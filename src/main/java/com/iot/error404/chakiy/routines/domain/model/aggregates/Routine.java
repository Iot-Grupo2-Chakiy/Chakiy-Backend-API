package com.iot.error404.chakiy.routines.domain.model.aggregates;

import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Routine extends AuditableAbstractAggregateRoot <Routine>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String dispositivo;

    private String condicion;

    public Long dias;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private String ubicacion;
}
