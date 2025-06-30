package com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IoTDeviceRepository extends JpaRepository<IoTDevice, Long> {
    boolean existsByName(String name);
}
