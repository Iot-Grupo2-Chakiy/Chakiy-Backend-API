package com.iot.error404.chakiy.iot.domain.model.aggregates;

import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class IoTDevice extends AuditableAbstractAggregateRoot<IoTDevice> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean estado;
    private Integer intervaloActualizar; // Update interval in seconds for the sensor data

    private Double temperaturaMin; // Minimum temperature threshold

    private Double temperaturaMax; // Maximum temperature threshold

    private Double calidadDeAireMin; // Minimum air quality threshold

    private Double calidadDeAireMax; // Maximum air quality threshold

    private Double humedadMin; // Minimum humidity threshold

    private Double humedadMax; // Maximum humidity threshold

    public IoTDevice(String name, Boolean estado, Integer intervaloActualizar,
                     Double temperaturaMin, Double temperaturaMax,
                     Double calidadDeAireMin, Double calidadDeAireMax,
                     Double humedadMin, Double humedadMax) {
        this.name = name;
        this.estado = estado;
        this.intervaloActualizar = intervaloActualizar;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.calidadDeAireMin = calidadDeAireMin;
        this.calidadDeAireMax = calidadDeAireMax;
        this.humedadMin = humedadMin;
        this.humedadMax = humedadMax;
    }

    public IoTDevice() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Integer getIntervaloActualizar() {
        return intervaloActualizar;
    }

    public Double getTemperaturaMin() {
        return temperaturaMin;
    }

    public Double getTemperaturaMax() {
        return temperaturaMax;
    }

    public Double getCalidadDeAireMin() {
        return calidadDeAireMin;
    }

    public Double getCalidadDeAireMax() {
        return calidadDeAireMax;
    }

    public Double getHumedadMin() {
        return humedadMin;
    }

    public Double getHumedadMax() {
        return humedadMax;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}