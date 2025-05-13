package com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories;

import com.iot.error404.chakiy.iot.domain.model.aggregates.SensorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDeviceRepository extends JpaRepository<SensorDevice, Long> {
    // Custom query methods can be defined here if needed
}
