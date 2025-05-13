package com.iot.error404.chakiy.iot.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class IoTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the IoT device

    @NotNull
    private String name; // Name of the IoT device

    @NotNull
    private Long sensorId; // Identifier for the associated sensor

    @NotNull
    private Boolean estado; // State of the device: true (ON), false (OFF)

    public IoTDevice(String name, Long sensorId, Boolean estado) {
        this.name = name;
        this.sensorId = sensorId;
        this.estado = estado;
    }

    public IoTDevice() {
    }
}