package com.iot.error404.chakiy.auditTrail.interfaces.REST.resources;

import java.time.LocalDateTime;

public record LogResource(
        Long id,
        LocalDateTime timestamp,
        String condition,
        String logType,
        Long deviceId
) {
}
