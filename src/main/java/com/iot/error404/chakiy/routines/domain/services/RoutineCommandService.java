package com.iot.error404.chakiy.routines.domain.services;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.domain.model.commands.CreateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.commands.DeleteRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.commands.UpdateRoutineCommand;

import java.util.Optional;

public interface RoutineCommandService {
    Optional<Routine> handle(CreateRoutineCommand command);
    Optional<Routine> handle(Long id, UpdateRoutineCommand command);
    void handle(DeleteRoutineCommand command);
}
