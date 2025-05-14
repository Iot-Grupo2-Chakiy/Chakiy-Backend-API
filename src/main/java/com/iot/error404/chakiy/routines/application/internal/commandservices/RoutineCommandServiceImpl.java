package com.iot.error404.chakiy.routines.application.internal.commandservices;

import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.IoTDeviceRepository;
import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.domain.model.commands.CreateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.commands.DeleteRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.commands.UpdateRoutineCommand;
import com.iot.error404.chakiy.routines.domain.services.RoutineCommandService;
import com.iot.error404.chakiy.routines.infrastructure.persistence.jpa.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoutineCommandServiceImpl implements RoutineCommandService {
    @Autowired private RoutineRepository routineRepository;
    @Autowired private IoTDeviceRepository ioTDeviceRepository;
    @Override
    public Optional<Routine> handle(CreateRoutineCommand command) {
        var iot = ioTDeviceRepository.findById(command.deviceId()).orElseThrow(
                () -> new IllegalArgumentException("Device not found with id: " + command.deviceId()));
        Routine routine = new Routine(command, iot);
        routineRepository.save(routine);
        return Optional.of(routine);
    }

    @Override
    public Optional<Routine> handle(Long id, UpdateRoutineCommand command) {
        Routine routine = routineRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Routine not found with id: " + id));
        var iot = ioTDeviceRepository.findById(command.deviceId()).orElseThrow(
                () -> new IllegalArgumentException("Device not found with id: " + command.deviceId()));
        routine.updateRoutine(command, iot);
        routineRepository.save(routine);
        return Optional.of(routine);
    }

    @Override
    public void handle(DeleteRoutineCommand command) {
        if (!routineRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Routine not found with id: " + command.id());
        }
        routineRepository.deleteById(command.id());
    }
}
