package com.iot.error404.chakiy.iot.interfaces.REST.transform;

import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTMainDeviceByIdCommand;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.UpdateIoTMainDeviceByIdCommandResource;

public class UpdateIoTMainDeviceByIdCommandFromResourceAssembler  {
    public static UpdateIoTMainDeviceByIdCommand toCommand(Long id, UpdateIoTMainDeviceByIdCommandResource resource) {
        return new UpdateIoTMainDeviceByIdCommand(id, resource.isMainDevice());
    }
}
