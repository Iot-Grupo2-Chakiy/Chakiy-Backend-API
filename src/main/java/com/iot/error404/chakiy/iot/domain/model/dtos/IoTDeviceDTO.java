package com.iot.error404.chakiy.iot.domain.model.dtos;

public class IoTDeviceDTO {
    private String name;
    private Boolean estado;
    private Integer intervaloActualizar;
    private Double temperaturaMin;
    private Double temperaturaMax;
    private Double calidadDeAireMin;
    private Double calidadDeAireMax;
    private Double humedadMin;
    private Double humedadMax;

    public IoTDeviceDTO(String name, Boolean estado, Integer intervaloActualizar,
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
}