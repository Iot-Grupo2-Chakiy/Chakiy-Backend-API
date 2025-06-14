package com.iot.error404.chakiy.routines.application.internal.backgroundservices;

import org.springframework.scheduling.annotation.Scheduled;

public class RoutineBackgroundServiceImpl {
    @Scheduled(fixedRate = 5000)

    public void ejecutarTareaPeriodica() {
        System.out.println("Ejecutando tarea en background: " + System.currentTimeMillis());
    }
}
