package com.iot.error404.chakiy.iot.domain.model.aggregates;

import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class IoTDevice extends AuditableAbstractAggregateRoot<IoTDevice> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long sensorId;
    private Boolean estado;

    public IoTDevice(String name, Long sensorId, Boolean estado) {
        this.name = name;
        this.sensorId = sensorId;
        this.estado = estado;
    }

    public IoTDevice() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}