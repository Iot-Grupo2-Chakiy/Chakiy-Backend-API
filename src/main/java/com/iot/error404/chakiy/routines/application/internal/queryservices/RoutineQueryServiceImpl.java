package com.iot.error404.chakiy.routines.application.internal.queryservices;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.domain.model.queries.GetAllRoutinesQuery;
import com.iot.error404.chakiy.routines.domain.model.queries.GetRoutineByIdQuery;
import com.iot.error404.chakiy.routines.domain.services.RoutineQueryService;
import com.iot.error404.chakiy.routines.infrastructure.persistence.jpa.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineQueryServiceImpl implements RoutineQueryService {
    @Autowired private RoutineRepository routineRepository;

    @Override
    public List<Routine> handle(GetAllRoutinesQuery query) {
        return routineRepository.findAll();
    }

    @Override
    public Routine handle(GetRoutineByIdQuery query) {
        return routineRepository.findById(query.id()).orElseThrow(
                () -> new IllegalArgumentException("Routine not found with id: " + query.id())
        );
    }
}
