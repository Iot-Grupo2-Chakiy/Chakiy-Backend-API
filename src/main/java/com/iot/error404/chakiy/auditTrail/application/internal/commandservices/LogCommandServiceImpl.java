package com.iot.error404.chakiy.auditTrail.application.internal.commandservices;

import com.iot.error404.chakiy.auditTrail.domain.model.aggregates.Log;
import com.iot.error404.chakiy.auditTrail.domain.model.commands.CreateLogCommand;
import com.iot.error404.chakiy.auditTrail.domain.services.LogCommandService;
import com.iot.error404.chakiy.auditTrail.infrastructure.persistence.jpa.repositories.LogRepository;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.IoTDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogCommandServiceImpl implements LogCommandService {
    @Autowired private LogRepository logRepository;
    @Autowired private IoTDeviceRepository ioTDeviceRepository;

    @Override
    public Optional<Log> handle(CreateLogCommand command) {
        var iot = ioTDeviceRepository.findById(command.deviceId()).orElseThrow(
                () -> new IllegalArgumentException("Device not found with id: " + command.deviceId()));
        var log = new Log(command, iot);
        logRepository.save(log);
        return Optional.of(log);
    }
}
