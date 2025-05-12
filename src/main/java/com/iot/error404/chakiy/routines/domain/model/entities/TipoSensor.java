package com.iot.error404.chakiy.routines.domain.model.entities;

import com.iot.error404.chakiy.routines.domain.model.aggregates.SensorDevice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class TipoSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "tipoSensorId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorDevice> sensorDevices;

    public TipoSensor() {
    }

    public TipoSensor(String name) {
        this.name = name;
    }
}