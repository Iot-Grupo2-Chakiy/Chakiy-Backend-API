package com.iot.error404.chakiy.iot.interfaces.REST.transform;

import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.CreateIoTDeviceResource;

public class CreateIoTDeviceCommandFromResourceAssembler {
    public static CreateIoTDeviceCommand toCommand(CreateIoTDeviceResource resource) {
        return new CreateIoTDeviceCommand(
                resource.name(),
                resource.estado(),
                resource.intervaloActualizar(),
                resource.temperaturaMin(),
                resource.temperaturaMax(),
                resource.calidadDeAireMin(),
                resource.calidadDeAireMax(),
                resource.humedadMin(),
                resource.humedadMax()
        );
    }
}