package com.iot.error404.chakiy.routines.domain.model.commands;

import java.time.LocalTime;
import java.util.List;

public record CreateRoutineCommand(String name, Long deviceId, String condition, List<String> days, LocalTime startTime, LocalTime endTime, String ubication) {
}
