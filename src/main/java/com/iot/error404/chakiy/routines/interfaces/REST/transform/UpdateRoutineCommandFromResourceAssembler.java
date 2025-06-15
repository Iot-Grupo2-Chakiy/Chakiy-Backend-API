package com.iot.error404.chakiy.routines.interfaces.REST.transform;


import com.iot.error404.chakiy.routines.domain.model.commands.UpdateRoutineCommand;
import com.iot.error404.chakiy.routines.interfaces.REST.resources.UpdateRoutineResource;

public class UpdateRoutineCommandFromResourceAssembler {
    public static UpdateRoutineCommand toCommandFromResource(UpdateRoutineResource resource) {
        return new UpdateRoutineCommand(
                resource.name(),
                resource.deviceId(),
                resource.condition(),
                resource.days(),
                resource.startTime(),
                resource.endTime(),
                resource.ubication(),
                resource.isDry()
        );
    }
}
