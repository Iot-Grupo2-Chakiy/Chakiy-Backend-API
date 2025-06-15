package com.iot.error404.chakiy.routines.interfaces.REST.resources;

import java.time.LocalTime;
import java.util.List;

public record CreateRoutineResource(
        String name,
        Long deviceId,
        String condition,
        List<String> days,
        LocalTime startTime,
        LocalTime endTime,
        String ubication,
        boolean isDry
) {
}
