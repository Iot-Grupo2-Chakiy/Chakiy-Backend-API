package com.iot.error404.chakiy.notifications.domain.model.aggregates;

import com.iot.error404.chakiy.notifications.domain.model.valueobjects.ENotificationType;
import com.iot.error404.chakiy.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
public class Notification extends AuditableAbstractAggregateRoot <Notification> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String body;

    private ENotificationType type;

    private String jsonReglas;

    public Notification() {

    }
}
