package com.iot.error404.chakiy.iot.interfaces.REST.transform;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.IoTDeviceResource;

public class IoTDeviceResourceFromEntityAssembler {
    public static IoTDeviceResource toResource(IoTDevice entity) {
        return new IoTDeviceResource(
                entity.getId(),
                entity.getName(),
                entity.getEstado(),
                entity.getIntervaloActualizar(),
                entity.getTemperaturaMin(),
                entity.getTemperaturaMax(),
                entity.getCalidadDeAireMin(),
                entity.getCalidadDeAireMax(),
                entity.getHumedadMin(),
                entity.getHumedadMax()
        );
    }
}