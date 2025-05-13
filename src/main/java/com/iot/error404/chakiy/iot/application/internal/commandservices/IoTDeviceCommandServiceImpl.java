package com.iot.error404.chakiy.iot.application.internal.commandservices;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceCommandService;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.IoTDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IoTDeviceCommandServiceImpl implements IoTDeviceCommandService {

    private final IoTDeviceRepository iotDeviceRepository;

    @Autowired
    public IoTDeviceCommandServiceImpl(IoTDeviceRepository iotDeviceRepository) {
        this.iotDeviceRepository = iotDeviceRepository;
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
        IoTDevice device = new IoTDevice(command.name(), command.sensorId(), command.estado());
        iotDeviceRepository.save(device);
        return device;
    }
    @Override
    public void createIoTDevice(CreateIoTDeviceCommand command) {
        createAndSaveIoTDevice(command);
    }
}