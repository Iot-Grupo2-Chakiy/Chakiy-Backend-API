package com.iot.error404.chakiy.auditTrail.domain.model.aggregates;

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
    private String level;
    private String message;
    private String source;

    public Log() {
        this.timestamp = LocalDateTime.now(); // Automatically set the timestamp
    }

    public Log(String level, String message, String source) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.message = message;
        this.source = source;
    }
}