package com.iot.error404.chakiy.iot.application.internal.commandservices;

import com.iot.error404.chakiy.auditTrail.domain.model.commands.CreateLogCommand;
import com.iot.error404.chakiy.auditTrail.domain.services.LogCommandService;
import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTMainDeviceByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIotEstadoByIdCommand;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceCommandService;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.IoTDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.iot.error404.chakiy.auditTrail.domain.model.valueobjects.LogType.MANUAL;

@Service
public class IoTDeviceCommandServiceImpl implements IoTDeviceCommandService {

    private final IoTDeviceRepository iotDeviceRepository;
    private final LogCommandService logCommandService;

    @Autowired
    public IoTDeviceCommandServiceImpl(IoTDeviceRepository iotDeviceRepository, LogCommandService logCommandService) {
        this.iotDeviceRepository = iotDeviceRepository;
        this.logCommandService = logCommandService;
    }

    @Override
    public void updateEstadoIoTDevice(UpdateIotEstadoByIdCommand command) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(command.id());
        if (optionalDevice.isPresent()) {
            IoTDevice device = optionalDevice.get();
            device.setEstado(command.estado());
            iotDeviceRepository.save(device);

            CreateLogCommand logCommand = new CreateLogCommand(
                    "Estado updated to: " + command.estado(),
                    "MANUAL",
                    command.id()
            );
            logCommandService.handle(logCommand);
        } else {
            throw new IllegalArgumentException("IoTDevice with id " + command.id() + " not found");
        }
    }

    @Override
    public void updateIoTMainDeviceById(UpdateIoTMainDeviceByIdCommand command) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(command.id());
        if (optionalDevice.isPresent()) {
            IoTDevice device = optionalDevice.get();
            device.setMainDevice(command.isMainDevice());
            iotDeviceRepository.save(device);
        } else {
            throw new IllegalArgumentException("IoTDevice with id " + command.id() + " not found");
        }
    }

    @Override
    public Long handle(CreateIoTDeviceCommand command) {
        return createAndSaveIoTDevice(command).getId();
    }

    @Override
    public void save(IoTDevice device) {
        iotDeviceRepository.save(device);
    }

    private IoTDevice createAndSaveIoTDevice(CreateIoTDeviceCommand command) {
        IoTDevice device = new IoTDevice(
                command.name(),
                command.estado(),
                command.intervaloActualizar(),
                command.temperaturaMin(),
                command.temperaturaMax(),
                command.calidadDeAireMin(),
                command.calidadDeAireMax(),
                command.humedadMin(),
                command.humedadMax(),
                command.isMainDevice()
        );
        iotDeviceRepository.save(device);
        return device;
    }

    @Override
    public void createIoTDevice(CreateIoTDeviceCommand command) {
        createAndSaveIoTDevice(command);
    }
}