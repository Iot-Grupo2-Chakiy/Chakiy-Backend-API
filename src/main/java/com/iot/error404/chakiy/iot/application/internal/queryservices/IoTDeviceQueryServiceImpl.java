package com.iot.error404.chakiy.iot.application.internal.queryservices;

import com.iot.error404.chakiy.iot.domain.model.aggregates.IoTDevice;
import com.iot.error404.chakiy.iot.domain.model.queries.GetAllIoTDevicesQuery;
import com.iot.error404.chakiy.iot.domain.model.queries.GetIoTDeviceByIdQuery;
import com.iot.error404.chakiy.iot.domain.services.IoTDeviceQueryService;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.IoTDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IoTDeviceQueryServiceImpl implements IoTDeviceQueryService {
    @Autowired private IoTDeviceRepository iotDeviceRepository;
    @Override
    public List<IoTDevice> handle(GetAllIoTDevicesQuery query) {
        return iotDeviceRepository.findAll();
    }

    @Override
    public Object handle(GetIoTDeviceByIdQuery query) {
        // Mock implementation - replace with actual logic
        return new Object();
    }
}