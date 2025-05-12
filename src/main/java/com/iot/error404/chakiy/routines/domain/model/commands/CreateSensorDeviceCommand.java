package com.iot.error404.chakiy.routines.domain.model.commands;

public record CreateSensorDeviceCommand(
        String name,
        Double temperatura, Long tipoSensorId, // Identificador del tipo de sensor
        Boolean estadoSensor,
        String calidadDeAire,
        Double humedad,
        Integer intervaloActualizar
) {
}