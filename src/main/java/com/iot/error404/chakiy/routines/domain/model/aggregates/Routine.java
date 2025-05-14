package com.iot.error404.chakiy.routines.domain.model.aggregates;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.routines.domain.model.commands.CreateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.commands.UpdateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.valueobjects.WeekDay;
import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Routine extends AuditableAbstractAggregateRoot <Routine>  {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private IoTDevice device;

    @Column(name = "routine_condition")
    private String condition;

    @ElementCollection(targetClass = WeekDay.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<WeekDay> days = new ArrayList<>();

    private LocalTime startTime;

    private LocalTime endTime;

    private String ubication;

    public Routine(CreateRoutineCommand command, IoTDevice device) {
        this.name = command.name();
        this.device = device;
        this.condition = command.condition();
        this.days = command.days().stream()
                .map(WeekDay::valueOf)
                .toList();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.ubication = command.ubication();
    }

    public void updateRoutine(UpdateRoutineCommand command, IoTDevice device) {
        this.name = command.name();
        this.device = device;
        this.condition = command.condition();
        this.days = command.days().stream()
                .map(WeekDay::valueOf)
                .toList();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.ubication = command.ubication();
    }

}
