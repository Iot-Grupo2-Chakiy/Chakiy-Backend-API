package com.iot.error404.chakiy.routines.application.internal.queryservices;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.domain.model.queries.GetAllRoutinesQuery;
import com.iot.error404.chakiy.routines.domain.model.queries.GetRoutineByIdQuery;
import com.iot.error404.chakiy.routines.domain.services.RoutineQueryService;
import com.iot.error404.chakiy.routines.infrastructure.persistence.jpa.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineQueryServiceImpl implements RoutineQueryService {
    @Autowired
    private RoutineRepository routineRepository;

    @Override
    public List<Routine> find_by_id_routine(GetAllRoutinesQuery query) {
        return routineRepository.findAll();
    }

    @Override
    public Routine find_by_id_routine(GetRoutineByIdQuery query) {
        return routineRepository.findByIdWithDevice(query.id())
                .orElseThrow(() -> new RuntimeException("Routine not found with id: " + query.id()));
    }
}