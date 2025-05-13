package com.iot.error404.chakiy.iot.domain.services;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;

public interface IoTDeviceCommandService {

    void createIoTDevice(CreateIoTDeviceCommand command);
    void save(IoTDevice device);
    Long handle(CreateIoTDeviceCommand command);
}