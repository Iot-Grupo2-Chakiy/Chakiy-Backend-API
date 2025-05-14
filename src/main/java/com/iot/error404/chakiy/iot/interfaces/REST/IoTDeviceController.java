package com.iot.error404.chakiy.iot.interfaces.REST;

import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIotEstadoByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.queries.GetAllIoTDevicesQuery;
import com.iot.error404.chakiy.iot.domain.model.queries.GetIoTDeviceByIdQuery;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceCommandService;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceQueryService;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.CreateIoTDeviceResource;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.UpdateIoTEstadoByIdResource;
import com.iot.error404.chakiy.iot.interfaces.REST.transform.UpdateIoTDeviceCommandFromResourceAssembler;
import com.iot.error404.chakiy.iot.interfaces.REST.transform.CreateIoTDeviceCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        UpdateIotEstadoByIdCommand command = UpdateIoTDeviceCommandFromResourceAssembler.toCommand(id, resource);
        iotDeviceService.updateEstadoIoTDevice(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllIoTDevices() {
        List<Object> devices = iotDeviceQueryService.handle(new GetAllIoTDevicesQuery());
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIoTDeviceById(@PathVariable Long id) {
        Object device = iotDeviceQueryService.handle(new GetIoTDeviceByIdQuery(id));
        return ResponseEntity.ok(device);
    }
}