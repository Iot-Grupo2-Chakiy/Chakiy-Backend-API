package com.iot.error404.chakiy.iot.application.internal.commandservices;

import com.iot.error404.chakiy.iot.domain.model.aggregates.SensorDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateSensorDeviceCommand;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.SensorDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorCommandServiceImpl {

    private final SensorDeviceRepository sensorDeviceRepository;

    @Autowired
    public SensorCommandServiceImpl(SensorDeviceRepository sensorDeviceRepository) {
        this.sensorDeviceRepository = sensorDeviceRepository;
    }

    public SensorDevice createSensorDevice(CreateSensorDeviceCommand command) {
        SensorDevice sensorDevice = new SensorDevice(
                command.name(),
                command.temperatura(),
                command.tipoSensorId(),
                command.estadoSensor(),
                command.calidadDeAire(),
                command.humedad(),
                command.intervaloActualizar()
        );
        return sensorDeviceRepository.save(sensorDevice);
    }
}