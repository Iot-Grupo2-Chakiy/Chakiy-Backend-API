package com.iot.error404.chakiy.iot.domain.services;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateIoTDeviceCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIoTMainDeviceByIdCommand;
import com.iot.error404.chakiy.iot.domain.model.commands.UpdateIotEstadoByIdCommand;

public interface IoTDeviceCommandService {

    void createIoTDevice(CreateIoTDeviceCommand command);
    void save(IoTDevice device);
    Long handle(CreateIoTDeviceCommand command);
    void updateEstadoIoTDevice(UpdateIotEstadoByIdCommand command);
    void updateIoTMainDeviceById(UpdateIoTMainDeviceByIdCommand command);
}