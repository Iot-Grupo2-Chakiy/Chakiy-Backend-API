package com.iot.error404.chakiy.iot.application.internal.commandservices;

import com.iot.error404.chakiy.auditTrail.domain.model.commands.CreateLogCommand;
import com.iot.error404.chakiy.auditTrail.domain.services.LogCommandService;
import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTDeviceByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTMainDeviceByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIotEstadoByIdCommand;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceCommandService;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.IoTDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class IoTDeviceCommandServiceImpl implements IoTDeviceCommandService {

    private static final String EDGE_API_URL = "https://563d-2001-1388-1641-b3ab-acbe-c469-5333-3788.ngrok-free.app/api/v1/health-dehumidifier";
    private static final String API_KEY = "apichakiykey";

    private final IoTDeviceRepository iotDeviceRepository;
    private final LogCommandService logCommandService;
    private final RestTemplate restTemplate = new RestTemplate();


    @Autowired
    public IoTDeviceCommandServiceImpl(IoTDeviceRepository iotDeviceRepository, LogCommandService logCommandService) {
        this.iotDeviceRepository = iotDeviceRepository;
        this.logCommandService = logCommandService;
    }


    private void updateEstadoInExternalAPI(Long deviceId, Boolean estado) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(deviceId);
        if (!optionalDevice.isPresent()) {
            throw new IllegalArgumentException("IoTDevice with id " + deviceId + " not found");
        }

        IoTDevice device = optionalDevice.get();

        Map<String, Object> payload = Map.of(
                "device_id", device.getName(), // Usar el nombre como device_id
                "estado", estado
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-API-Key", API_KEY);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            String url = EDGE_API_URL + "/update-estado";
            System.out.println("Sending update estado payload: " + payload);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    request,
                    String.class
            );
            System.out.println("Estado updated successfully in Edge API for device: " + device.getName());
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error updating estado in Edge API: " + e.getMessage());
        }
    }
    @Override
    public void updateEstadoIoTDevice(UpdateIotEstadoByIdCommand command) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(command.id());
        if (optionalDevice.isPresent()) {
            IoTDevice device = optionalDevice.get();
            device.setEstado(command.estado());
            iotDeviceRepository.save(device);

            // Actualizar estado en Edge API
            try {
                updateEstadoInExternalAPI(command.id(), command.estado());
            } catch (Exception e) {
                System.err.println("Failed to update estado in Edge API: " + e.getMessage());
            }

            CreateLogCommand logCommand = new CreateLogCommand(
                    "Estado updated to: " + command.estado(),
                    "MANUAL",
                    command.id()
            );
            logCommandService.handle(logCommand);
        } else {
            throw new IllegalArgumentException("IoTDevice with id " + command.id() + " not found");
        }
    }

    @Override
    public Map<String, Boolean> updateIoTMainDeviceById(UpdateIoTMainDeviceByIdCommand command) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(command.id());

        if (optionalDevice.isPresent()) {
            IoTDevice device = optionalDevice.get();

            boolean isTryingToSetAsMain = command.isMainDevice();

            if (isTryingToSetAsMain) {
                boolean anotherMainDeviceExists = iotDeviceRepository.findAll()
                        .stream()
                        .anyMatch(d -> d.getIsMainDevice() != null && d.getIsMainDevice() && !d.getId().equals(device.getId()));

                if (anotherMainDeviceExists) {
                    return Map.of("doesMainDeviceAlreadyExists", true);
                }
            }

            device.setMainDevice(command.isMainDevice());
            iotDeviceRepository.save(device);
            return Map.of("doesMainDeviceAlreadyExists", false);
        } else {
            throw new IllegalArgumentException("IoTDevice with id " + command.id() + " not found");
        }
    }


    @Override
    public boolean updateIoTDeviceById(UpdateIoTDeviceByIdCommand command) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(command.id());
        if (optionalDevice.isPresent()) {
            IoTDevice device = optionalDevice.get();

            device.setName(command.name());
            device.setEstado(command.estado());
            device.setIntervaloActualizar(command.intervaloActualizar());
            device.setTemperaturaMin(command.temperaturaMin());
            device.setTemperaturaMax(command.temperaturaMax());
            device.setCalidadDeAireMin(command.calidadDeAireMin());
            device.setCalidadDeAireMax(command.calidadDeAireMax());
            device.setHumedadMin(command.humedadMin());
            device.setHumedadMax(command.humedadMax());
            device.setMainDevice(command.isMainDevice());

            iotDeviceRepository.save(device);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long handle(CreateIoTDeviceCommand command) {
        IoTDevice device = createAndSaveIoTDevice(command);
        return device.getId();
    }

    private void sendIoTDeviceInfoToExternalAPI(IoTDevice device) {
        Map<String, Object> payload = Map.of(
                "device_id", device.getName(), // Corrected field name from "device_id" to "device_name"
                "humidifier_info", Map.of(
                        "estado", device.getEstado(),
                        "temperaturaMin", device.getTemperaturaMin(),
                        "temperaturaMax", device.getTemperaturaMax(),
                        "calidadDeAireMin", device.getCalidadDeAireMin(),
                        "calidadDeAireMax", device.getCalidadDeAireMax(),
                        "humedadMin", device.getHumedadMin(),
                        "humedadMax", device.getHumedadMax()
                ),
                "created_at", java.time.Instant.now().toString()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", API_KEY);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            String url = EDGE_API_URL + "/create-dehumidifier";
            System.out.println("Sending payload: " + payload);
            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);
            System.out.println("IoTDevice info sent successfully for device: " + device.getName());
        } catch (Exception e) {
            System.err.println("Error sending IoTDevice info to external API: " + e.getMessage());
        }
    }

    private IoTDevice createAndSaveIoTDevice(CreateIoTDeviceCommand command) {
        boolean deviceExists = iotDeviceRepository.existsByName(command.name());
        if (deviceExists) {
            throw new IllegalArgumentException("IoTDevice with name '" + command.name() + "' already exists");
        }

        IoTDevice device = new IoTDevice(
                command.name(),
                command.estado(),
                command.intervaloActualizar(),
                command.temperaturaMin(),
                command.temperaturaMax(),
                command.calidadDeAireMin(),
                command.calidadDeAireMax(),
                command.humedadMin(),
                command.humedadMax(),
                command.isMainDevice()
        );
        iotDeviceRepository.save(device);
        sendIoTDeviceInfoToExternalAPI(device);
        return device;
    }

    @Override
    public void save(IoTDevice device) {
        iotDeviceRepository.save(device);
    }

    @Override
    public void createIoTDevice(CreateIoTDeviceCommand command) {
        createAndSaveIoTDevice(command);
    }

    @Override
    public void deleteIoTDeviceById(Long id) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(id);
        if (optionalDevice.isPresent()) {
            iotDeviceRepository.delete(optionalDevice.get());
        } else {
            throw new IllegalArgumentException("IoTDevice with id " + id + " not found");
        }
    }


}
