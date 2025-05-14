package com.iot.error404.chakiy.iot.application.internal.queryservices;

import com.iot.error404.chakiy.iot.domain.model.queries.GetAllIoTDevicesQuery;
import com.iot.error404.chakiy.iot.domain.model.queries.GetIoTDeviceByIdQuery;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceQueryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IoTDeviceQueryServiceImpl implements IoTDeviceQueryService {

    @Override
    public List<Object> handle(GetAllIoTDevicesQuery query) {
        // Mock implementation - replace with actual logic
        return new ArrayList<>();
    }

    @Override
    public Object handle(GetIoTDeviceByIdQuery query) {
        // Mock implementation - replace with actual logic
        return new Object();
    }
}