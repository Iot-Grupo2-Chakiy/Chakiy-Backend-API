package com.iot.error404.chakiy.routines.interfaces.REST.resources;

import com.iot.error404.chakiy.iot.interfaces.REST.resources.IoTDeviceResource;

import java.time.LocalTime;
import java.util.List;

public record RoutineResource(
        Long id,
        String name,
        IoTDeviceResource device,
        String condition,
        List<String> days,
        LocalTime startTime,
        LocalTime endTime,
        String ubication
) {
}
