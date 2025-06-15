package com.iot.error404.chakiy.iot.domain.model.commands;

public record CreateIoTDeviceCommand(
        String name,
        Boolean estado,
        Integer intervaloActualizar,
        Double temperaturaMin,
        Double temperaturaMax,
        Double calidadDeAireMin,
        Double calidadDeAireMax,
        Double humedadMin,
        Double humedadMax,
        Boolean isMainDevice
) {
}