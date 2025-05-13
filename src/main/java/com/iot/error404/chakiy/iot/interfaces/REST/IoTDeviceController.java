package com.iot.error404.chakiy.iot.interfaces.REST;
import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceCommandService;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.CreateIoTDeviceResource;
import com.iot.error404.chakiy.iot.interfaces.REST.resources.IoTDeviceResource;
import com.iot.error404.chakiy.iot.interfaces.REST.transform.CreateIoTDeviceCommandFromResourceAssembler;
import com.iot.error404.chakiy.iot.interfaces.REST.transform.IoTDeviceResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/iot-devices")
public class IoTDeviceController {

    private final IoTDeviceCommandService iotDeviceService;

    public IoTDeviceController(IoTDeviceCommandService iotDeviceService) {
        this.iotDeviceService = iotDeviceService;
    }

    @PostMapping
    public ResponseEntity<IoTDeviceResource> createIoTDevice(@RequestBody CreateIoTDeviceResource resource) {
        CreateIoTDeviceCommand command = CreateIoTDeviceCommandFromResourceAssembler.toCommand(resource);
        IoTDevice device = iotDeviceService.createIoTDevice(command);
        IoTDeviceResource deviceResource = IoTDeviceResourceFromEntityAssembler.toResource(device);
        return new ResponseEntity<>(deviceResource, HttpStatus.CREATED);
    }
}