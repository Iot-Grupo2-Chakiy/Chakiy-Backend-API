package com.iot.error404.chakiy.auditTrail.domain.model.aggregates;

import com.iot.error404.chakiy.auditTrail.domain.model.commands.CreateLogCommand;
import com.iot.error404.chakiy.auditTrail.domain.model.valueobjects.LogType;
import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Log extends AuditableAbstractAggregateRoot <Log> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Column(name = "log_condition")
    private String condition;

    @Enumerated(EnumType.STRING)
    private LogType logType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = true)
    private IoTDevice ioTDevice;

    public Log() {
        this.timestamp = LocalDateTime.now(); // Automatically set the timestamp
    }

    public Log(CreateLogCommand command, IoTDevice device) {
        this.timestamp = LocalDateTime.now();
        this.condition = command.condition();
        this.logType = LogType.valueOf(command.logType());
        this.ioTDevice = device;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCondition() {
        return condition;
    }

    public Long getId()
    {
        return id;
    }

    public LogType getLogType() {
        return logType;
    }

    public IoTDevice getIoTDevice() {
        return ioTDevice;
    }
}