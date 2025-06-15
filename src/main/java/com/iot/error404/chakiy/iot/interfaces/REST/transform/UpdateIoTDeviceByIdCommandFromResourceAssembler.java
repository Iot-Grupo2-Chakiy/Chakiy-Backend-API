package com.iot.error404.chakiy.iot.interfaces.REST.transform;

import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTDeviceByIdCommand;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.UpdateIoTDeviceByIdResource;

public class UpdateIoTDeviceByIdCommandFromResourceAssembler {
    public static UpdateIoTDeviceByIdCommand toCommand(Long id, UpdateIoTDeviceByIdResource resource) {
        return new UpdateIoTDeviceByIdCommand(
                id,
                resource.name(),
                resource.estado(),
                resource.intervaloActualizar(),
                resource.temperaturaMin(),
                resource.temperaturaMax(),
                resource.calidadDeAireMin(),
                resource.calidadDeAireMax(),
                resource.humedadMin(),
                resource.humedadMax(),
                resource.isMainDevice()
        );
    }
}