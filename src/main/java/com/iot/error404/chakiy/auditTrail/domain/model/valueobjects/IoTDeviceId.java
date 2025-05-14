package com.iot.error404.chakiy.auditTrail.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

@Embeddable
public record IoTDeviceId(Long iotDeviceId) {
    public IoTDeviceId {
        if (iotDeviceId < 0) {
            throw new IllegalArgumentException("Profile profileId cannot be negative");
        }
    }

    public IoTDeviceId() { this(0L); }

    public long IoTDeviceIdAsPrimitive() {
        return iotDeviceId != null ? iotDeviceId : 0L;
    }
}
