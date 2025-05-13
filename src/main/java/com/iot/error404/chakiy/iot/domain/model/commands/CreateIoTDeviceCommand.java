package com.iot.error404.chakiy.iot.domain.model.commands;

public record CreateIoTDeviceCommand(
        String name,
        Long sensorId,
        Boolean estado
) {
}