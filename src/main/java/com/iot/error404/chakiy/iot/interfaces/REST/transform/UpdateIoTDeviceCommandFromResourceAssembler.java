package com.iot.error404.chakiy.iot.interfaces.REST.transform;

import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIotEstadoByIdCommand;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.UpdateIoTEstadoByIdResource;

public class UpdateIoTDeviceCommandFromResourceAssembler {

    public static UpdateIotEstadoByIdCommand toCommand(Long id, UpdateIoTEstadoByIdResource resource) {
        return new UpdateIotEstadoByIdCommand(id, resource.estado());
    }
}