package com.iot.error404.chakiy.routines.interfaces.REST.transform;

import com.iot.error404.chakiy.routines.domain.model.commands.CreateRoutineCommand;
import com.iot.error404.chakiy.routines.interfaces.REST.resources.CreateRoutineResource;

public class CreateRoutineCommandFromResourceAssembler {
    public static CreateRoutineCommand toCommandFromResource(CreateRoutineResource resource) {
        return new CreateRoutineCommand(
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
