package com.iot.error404.chakiy.routines.interfaces.REST.transform;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.interfaces.REST.resources.RoutineResource;

public class RoutineResourceFromEntityAssembler {
    public static RoutineResource toResourceFromEntity(Routine entity) {
        return new RoutineResource(
                entity.getId(),
                entity.getName(),
                entity.getDevice().getId(),
                entity.getCondition(),
                entity.getDays().stream().map(Enum::name).toList(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getUbication()
                );
    }
}
