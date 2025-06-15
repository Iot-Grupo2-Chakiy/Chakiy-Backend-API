package com.iot.error404.chakiy.iot.domain.model.aggregates;

import com.iot.error404.chakiy.auditTrail.domain.model.aggregates.Log;
import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class IoTDevice extends AuditableAbstractAggregateRoot<IoTDevice> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean estado;
    private Integer intervaloActualizar;
    private Double temperaturaMin;

    private Double temperaturaMax;
    @OneToMany(mappedBy = "device", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Routine> routines = new ArrayList<>();

    private Double calidadDeAireMin;
    private Double calidadDeAireMax;
    private Double humedadMin;
    private Double humedadMax;
    public Boolean isMainDevice;
    public IoTDevice(String name, Boolean estado, Integer intervaloActualizar,
                     Double temperaturaMin, Double temperaturaMax,
                     Double calidadDeAireMin, Double calidadDeAireMax,
                     Double humedadMin, Double humedadMax, Boolean isMainDevice) {
        this.name = name;
        this.estado = estado;
        this.intervaloActualizar = intervaloActualizar;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.calidadDeAireMin = calidadDeAireMin;
        this.calidadDeAireMax = calidadDeAireMax;
        this.humedadMin = humedadMin;
        this.humedadMax = humedadMax;
        this.isMainDevice = isMainDevice;
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
    public Boolean getIsMainDevice() {
        return isMainDevice;
    }
    public void setMainDevice(Boolean isMainDevice) {
        this.isMainDevice = isMainDevice;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntervaloActualizar(Integer intervaloActualizar) {
        this.intervaloActualizar = intervaloActualizar;
    }

    public void setTemperaturaMin(Double temperaturaMin) {
        this.temperaturaMin = temperaturaMin;
    }

    public void setTemperaturaMax(Double temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }

    public void setCalidadDeAireMin(Double calidadDeAireMin) {
        this.calidadDeAireMin = calidadDeAireMin;
    }

    public void setCalidadDeAireMax(Double calidadDeAireMax) {
        this.calidadDeAireMax = calidadDeAireMax;
    }

    public void setHumedadMin(Double humedadMin) {
        this.humedadMin = humedadMin;
    }

    public void setHumedadMax(Double humedadMax) {
        this.humedadMax = humedadMax;
    }
}