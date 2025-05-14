package com.iot.error404.chakiy.routines.domain.model.aggregates;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.routines.domain.model.commands.CreateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.commands.UpdateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.valueobjects.WeekDay;
import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Routine extends AuditableAbstractAggregateRoot <Routine>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private IoTDevice device;

    private String condition;

    @ElementCollection(targetClass = WeekDay.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<WeekDay> days;

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
