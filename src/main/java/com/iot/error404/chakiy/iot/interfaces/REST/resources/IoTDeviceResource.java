package com.iot.error404.chakiy.iot.interfaces.REST.resources;

public record IoTDeviceResource(
        Long id,
        String name,
        Boolean estado,
        Integer intervaloActualizar,
        Double temperaturaMin,
        Double temperaturaMax,
        Double calidadDeAireMin,
        Double calidadDeAireMax,
        Double humedadMin,
        Double humedadMax
) {
}