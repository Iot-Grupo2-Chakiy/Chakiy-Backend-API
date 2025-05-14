package com.iot.error404.chakiy.auditTrail.domain.model.commands;


public record CreateLogCommand(String condition, String logType, Long deviceId) {
}
