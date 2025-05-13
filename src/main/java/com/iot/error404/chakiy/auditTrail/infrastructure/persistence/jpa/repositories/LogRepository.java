package com.iot.error404.chakiy.auditTrail.infrastructure.persistence.jpa.repositories;

import com.iot.error404.chakiy.auditTrail.domain.model.aggregates.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository <Log, Long> {
}
