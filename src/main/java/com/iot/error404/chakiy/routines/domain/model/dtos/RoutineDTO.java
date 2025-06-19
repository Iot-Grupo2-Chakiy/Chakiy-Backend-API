package com.iot.error404.chakiy.routines.domain.model.dtos;

import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class RoutineDTO {
    private Long id;
    private String name;
    private String condition;
    private List<String> days;
    private String startTime;
    private String endTime;
    private String ubication;
    private boolean isDry;
    private Date createdAt;
    private Long iotDeviceId;

    public RoutineDTO(Routine routine) {
        this.id = routine.getId();
        this.name = routine.getName();
        this.condition = routine.getCondition();
        this.days = routine.getDays().stream().map(Enum::name).toList();
        this.startTime = routine.getStartTime().toString();
        this.endTime = routine.getEndTime().toString();
        this.ubication = routine.getUbication();
        this.isDry = routine.getIsDry();
        this.createdAt = routine.getCreatedAt();
        this.iotDeviceId = routine.getDevice().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public List<String> getDays() {
        return days;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getUbication() {
        return ubication;
    }

    public boolean getIsDry() {
        return isDry;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getIotDeviceId() {
        return iotDeviceId;
    }
}