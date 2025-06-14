package com.iot.error404.chakiy.iot.interfaces.REST.resources;

public record CreateIoTDeviceResource(
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