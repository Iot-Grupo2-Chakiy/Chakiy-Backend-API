package com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories;

import com.iot.error404.chakiy.iot.domain.model.entities.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSensorRepository extends JpaRepository <TipoSensor, Long> {
}
