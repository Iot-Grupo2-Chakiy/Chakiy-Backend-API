package com.iot.error404.chakiy.iot.domain.services;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.queries.GetAllIoTDevicesQuery;
import com.iot.error404.chakiy.iot.domain.model.queries.GetIoTDeviceByIdQuery;

import java.util.List;

public interface IoTDeviceQueryService {
    List<IoTDevice> handle(GetAllIoTDevicesQuery query);
    Object handle(GetIoTDeviceByIdQuery query);
}