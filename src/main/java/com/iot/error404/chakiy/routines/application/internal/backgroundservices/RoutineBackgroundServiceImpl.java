package com.iot.error404.chakiy.routines.application.internal.backgroundservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.error404.chakiy.routines.domain.model.dtos.RoutineDTO;
import com.iot.error404.chakiy.routines.domain.model.queries.GetRoutineByIdQuery;
import com.iot.error404.chakiy.routines.domain.services.RoutineQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoutineBackgroundServiceImpl {
 /*
    private static final String ENDPOINT_URL = "http://127.0.0.1:5000/api/v1/routine-monitoring/data-records";
    private static final String API_KEY = "apichakiykey";

    @Autowired
    private RoutineQueryService routineQueryService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 10000)
    public void ejecutarTareaPeriodica() {
      GetRoutineByIdQuery query = new GetRoutineByIdQuery(1L);

        try {
            var routine = routineQueryService.find_by_id_routine(query);

            if (routine != null) {
                // Convert Routine to RoutineDTO for proper serialization
                RoutineDTO routineDTO = new RoutineDTO(routine);

                // Serialize the RoutineDTO object into a JSON string
                String routineData;
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    routineData = objectMapper.writeValueAsString(routineDTO);
                } catch (JsonProcessingException e) {
                    System.err.println("Error serializing routine to JSON: " + e.getMessage());
                    return;
                }

                // Hardcode the device_id as a String
                String deviceId = "dehumidifier_chakiy_001"; // Replace with the desired hardcoded value

                // Create the request body
                var requestBody = new RoutineRequest(
                        routineData, // Serialized JSON string
                        routine.getCreatedAt().toString(), // Assuming createdAt is a timestamp
                        deviceId // Hardcoded device_id as String
                );

                HttpHeaders headers = new HttpHeaders();
                headers.set("X-API-Key", API_KEY);

                HttpEntity<RoutineRequest> request = new HttpEntity<>(requestBody, headers);

                try {
                    ResponseEntity<String> response = restTemplate.postForEntity(ENDPOINT_URL, request, String.class);
                    System.out.println("Respuesta del servidor: " + response.getBody());
                } catch (Exception e) {
                    System.err.println("Error al realizar la petición POST: " + e.getMessage());
                }
            } else {
                System.err.println("No se encontró ninguna rutina para enviar.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener la rutina: " + e.getMessage());
        }
    }

    private static class RoutineRequest {
        private final String routine_data;
        private final String created_at;
        private final String device_id; // Changed to String

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

  */
}