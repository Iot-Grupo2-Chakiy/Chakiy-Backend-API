package com.iot.error404.chakiy.routines.domain.services;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.domain.model.queries.GetAllRoutinesQuery;
import com.iot.error404.chakiy.routines.domain.model.queries.GetRoutineByIdQuery;

import java.util.List;

public interface RoutineQueryService {
    List<Routine> find_by_id_routine(GetAllRoutinesQuery query);
    Routine find_by_id_routine(GetRoutineByIdQuery query);
}
