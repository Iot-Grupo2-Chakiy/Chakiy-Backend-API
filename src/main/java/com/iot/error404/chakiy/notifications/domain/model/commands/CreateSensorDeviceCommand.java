package com.iot.error404.chakiy.notifications.domain.model.commands;

public record CreateSensorDeviceCommand(
        String name,
        Double temperatura, Long tipoSensorId, // Identificador del tipo de sensor
        Boolean estadoSensor,
        String calidadDeAire,
        Double humedad,
        Integer intervaloActualizar
) {
}