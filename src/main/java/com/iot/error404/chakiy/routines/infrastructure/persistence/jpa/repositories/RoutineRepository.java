package com.iot.error404.chakiy.routines.infrastructure.persistence.jpa.repositories;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    @Query("SELECT r FROM Routine r JOIN FETCH r.device WHERE r.id = :id")
    Optional<Routine> findByIdWithDevice(@Param("id") Long id);
}