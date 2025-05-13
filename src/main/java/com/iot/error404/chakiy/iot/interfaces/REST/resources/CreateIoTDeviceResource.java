package com.iot.error404.chakiy.iot.interfaces.REST.resources;

public record CreateIoTDeviceResource(
        String name,
        Long sensorId,
        Boolean estado
) {
}