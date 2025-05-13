package com.iot.error404.chakiy.weatherInfo.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

@Getter
@Setter
@Entity
public class SensorDevice extends AbstractAggregateRoot<SensorDevice> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name; // Name of the sensor device

    @NotNull
    private Double temperatura; // Temperature value recorded by the sensor

    @NotNull
    private Long tipoSensorId; // Identifier for the type of sensor

    @NotNull
    private Boolean estadoSensor; // Sensor state: true for ON, false for OFF

    @NotNull
    private String calidadDeAire; // Air quality: e.g., "Buena" (Good)

    @NotNull
    private Double humedad; // Humidity value recorded by the sensor

    @NotNull
    private Integer intervaloActualizar; // Update interval in seconds for the sensor data

    private Date lastUpdated; // Timestamp of the last update

    public SensorDevice(String name, Double temperatura, Long tipoSensorId, Boolean estadoSensor, String calidadDeAire, Double humedad, Integer intervaloActualizar) {
        this.name = name;
        this.temperatura = temperatura;
        this.tipoSensorId = tipoSensorId;
        this.estadoSensor = estadoSensor;
        this.calidadDeAire = calidadDeAire;
        this.humedad = humedad;
        this.intervaloActualizar = intervaloActualizar;
        this.lastUpdated = new Date();
    }

    public SensorDevice() {
    }

    public void updateLastUpdated() {
        this.lastUpdated = new Date();
    }
}