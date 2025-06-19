package com.iot.error404.chakiy.routines.application.internal.commandservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.IoTDeviceRepository;
import com.iot.error404.chakiy.routines.domain.model.aggregates.Routine;
import com.iot.error404.chakiy.routines.domain.model.commands.*;
import com.iot.error404.chakiy.routines.domain.model.dtos.RoutineDTO;
import com.iot.error404.chakiy.routines.domain.services.RoutineCommandService;
import com.iot.error404.chakiy.routines.infrastructure.persistence.jpa.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RoutineCommandServiceImpl implements RoutineCommandService {
    private static final String EDGE_API_URL = "http://127.0.0.1:5000/api/v1/routine-monitoring/data-records";
    private static final String API_KEY = "apichakiykey";

    @Autowired private RoutineRepository routineRepository;
    @Autowired private IoTDeviceRepository ioTDeviceRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<Routine> handle(CreateRoutineCommand command) {
        var iot = ioTDeviceRepository.findById(command.deviceId()).orElseThrow(
                () -> new IllegalArgumentException("Device not found with id: " + command.deviceId()));
        Routine routine = new Routine(command, iot);
        routineRepository.save(routine);

        registerRoutineInExternalAPI(routine);

        return Optional.of(routine);
    }

    private void registerRoutineInExternalAPI(Routine routine) {
        RoutineDTO routineDTO = new RoutineDTO(routine);

        IoTDevice device = ioTDeviceRepository.findById(routine.getDevice().getId())
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + routine.getDevice().getId()));

        String routineData;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            routineData = objectMapper.writeValueAsString(routineDTO);
        } catch (JsonProcessingException e) {
            System.err.println("Error serializing routine to JSON: " + e.getMessage());
            return;
        }

        var requestBody = new RoutineRequest(
                routineData,
                java.time.Instant.now().toString(),
                device.getName()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", API_KEY);

        HttpEntity<RoutineRequest> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(EDGE_API_URL, request, String.class);
            System.out.println("Routine registered in external API: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error registering routine in external API: " + e.getMessage());
        }
    }

    private static class RoutineRequest {
        private final String routine_data;
        private final String created_at;
        private final String device_id;

        public RoutineRequest(String routine_data, String created_at, String device_id) {
            this.routine_data = routine_data;
            this.created_at = created_at;
            this.device_id = device_id;
        }

        public String getRoutine_data() {
            return routine_data;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getDevice_id() {
            return device_id;
        }
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
