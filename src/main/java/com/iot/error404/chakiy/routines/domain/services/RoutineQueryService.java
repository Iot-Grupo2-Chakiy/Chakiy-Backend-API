package com.iot.error404.chakiy.routines.domain.services;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.domain.model.queries.GetAllRoutinesQuery;
import com.iot.error404.chakiy.routines.domain.model.queries.GetRoutineByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RoutineQueryService {
    List<Routine> handle(GetAllRoutinesQuery query);
    Routine handle(GetRoutineByIdQuery query);
}
