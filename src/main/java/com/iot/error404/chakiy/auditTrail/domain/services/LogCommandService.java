package com.iot.error404.chakiy.auditTrail.domain.services;

import com.iot.error404.chakiy.auditTrail.domain.model.aggregates.Log;
import com.iot.error404.chakiy.auditTrail.domain.model.commands.CreateLogCommand;

import java.util.Optional;

public interface LogCommandService {
    Optional<Log> handle(CreateLogCommand command);
}
