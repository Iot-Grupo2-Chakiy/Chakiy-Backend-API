package com.iot.error404.chakiy.iot.interfaces.REST.resources;

public record IoTDeviceResource(
        Long id,
        String name,
        Long sensorId,
        Boolean estado
) {
}