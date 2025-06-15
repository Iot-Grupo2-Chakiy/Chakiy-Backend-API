package com.iot.error404.chakiy.iot.interfaces.REST;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTDeviceByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTMainDeviceByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIotEstadoByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.queries.GetAllIoTDevicesQuery;
import com.iot.error404.chakiy.iot.domain.model.queries.GetIoTDeviceByIdQuery;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceCommandService;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceQueryService;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.*;
import com.iot.error404.chakiy.iot.interfaces.REST.transform.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/iot-devices")
public class IoTDeviceController {

    private final IoTDeviceCommandService iotDeviceService;
    private final IoTDeviceQueryService iotDeviceQueryService;

    public IoTDeviceController(IoTDeviceCommandService iotDeviceService, IoTDeviceQueryService iotDeviceQueryService) {
        this.iotDeviceService = iotDeviceService;
        this.iotDeviceQueryService = iotDeviceQueryService;
    }

    @PostMapping
    public ResponseEntity<Void> createIoTDevice(@RequestBody CreateIoTDeviceResource resource) {
        CreateIoTDeviceCommand command = CreateIoTDeviceCommandFromResourceAssembler.toCommand(resource);
        iotDeviceService.createIoTDevice(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> updateIoTDeviceEstado(@PathVariable Long id, @RequestBody UpdateIoTEstadoByIdResource resource) {
        UpdateIotEstadoByIdCommand command = UpdateIoTDeviceEstadoCommandFromResourceAssembler.toCommand(id, resource);
        iotDeviceService.updateEstadoIoTDevice(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<IoTDeviceResource>> getAllIoTDevices() {
        List<IoTDevice> devices = iotDeviceQueryService.handle(new GetAllIoTDevicesQuery());
        List<IoTDeviceResource> deviceResources = devices.stream().map(IoTDeviceResourceFromEntityAssembler::toResource).toList();
        return ResponseEntity.ok(deviceResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIoTDeviceById(@PathVariable Long id) {
        Object device = iotDeviceQueryService.handle(new GetIoTDeviceByIdQuery(id));
        return ResponseEntity.ok(device);
    }

    @PatchMapping("/{id}/main-device")
    public ResponseEntity<Map<String, Boolean>> updateIoTMainDeviceById(@PathVariable Long id, @RequestBody UpdateIoTMainDeviceByIdCommandResource resource) {
        UpdateIoTMainDeviceByIdCommand command = UpdateIoTMainDeviceByIdCommandFromResourceAssembler.toCommand(id, resource);
        Map<String, Boolean> response = iotDeviceService.updateIoTMainDeviceById(command);

        if (response.get("doesMainDeviceAlreadyExists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIoTDeviceById(@PathVariable Long id) {
        iotDeviceService.deleteIoTDeviceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateIoTDeviceById(@PathVariable Long id, @RequestBody UpdateIoTDeviceByIdResource resource) {
        UpdateIoTDeviceByIdCommand command = UpdateIoTDeviceByIdCommandFromResourceAssembler.toCommand(id, resource);
        boolean updated = iotDeviceService.updateIoTDeviceById(command);

        if (updated) {
            return ResponseEntity.ok("El dispositivo IoT fue actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El dispositivo IoT no fue encontrado o no pudo ser actualizado.");
        }
    }
}