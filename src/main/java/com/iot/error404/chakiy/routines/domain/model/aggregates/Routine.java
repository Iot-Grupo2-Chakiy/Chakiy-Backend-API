package com.iot.error404.chakiy.routines.domain.model.aggregates;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.routines.domain.model.commands.CreateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.commands.UpdateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.valueobjects.WeekDay;
import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Routine extends AuditableAbstractAggregateRoot<Routine> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private boolean isDry;

    public Routine() {}

    public Routine(CreateRoutineCommand command, IoTDevice device) {
        this.name = command.name();
        this.device = device;
        this.condition = command.condition();
        this.days = command.days().stream()
                .map(WeekDay::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.ubication = command.ubication();
        this.isDry = command.isDry();
    }

    public void updateRoutine(UpdateRoutineCommand command, IoTDevice device) {
        this.name = command.name();
        this.device = device;
        this.condition = command.condition();
        this.days.clear();
        this.days.addAll(command.days().stream()
                .map(WeekDay::valueOf)
                .toList());
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.ubication = command.ubication();
        this.isDry = command.isDry();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IoTDevice getDevice() {
        return device;
    }

    public String getCondition() {
        return condition;
    }

    public List<WeekDay> getDays() {
        return days;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getUbication() {
        return ubication;
    }

    public boolean getIsDry() {
        return isDry;
    }
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }
}