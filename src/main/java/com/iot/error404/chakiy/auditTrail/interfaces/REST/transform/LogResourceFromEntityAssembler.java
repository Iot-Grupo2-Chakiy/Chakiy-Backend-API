package com.iot.error404.chakiy.auditTrail.interfaces.REST.transform;

import com.iot.error404.chakiy.auditTrail.domain.model.aggregates.Log;
import com.iot.error404.chakiy.auditTrail.interfaces.REST.resources.LogResource;

public class LogResourceFromEntityAssembler {
    public static LogResource toResourceFromEntity(Log entity) {
        return new LogResource(
                entity.getId(),
                entity.getTimestamp(),
                entity.getCondition(),
                entity.getLogType().name(),
                entity.getIoTDevice().getId()
        );
    }
}
