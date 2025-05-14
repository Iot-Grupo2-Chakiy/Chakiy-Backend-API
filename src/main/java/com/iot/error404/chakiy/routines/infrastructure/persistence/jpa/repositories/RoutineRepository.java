package com.iot.error404.chakiy.routines.infrastructure.persistence.jpa.repositories;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
