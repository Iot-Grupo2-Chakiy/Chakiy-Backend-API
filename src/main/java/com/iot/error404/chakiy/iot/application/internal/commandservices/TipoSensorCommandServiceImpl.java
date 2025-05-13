package com.iot.error404.chakiy.iot.application.internal.commandservices;

import com.iot.error404.chakiy.iot.domain.model.entities.TipoSensor;
import com.iot.error404.chakiy.iot.domain.model.commands.CreateTipoSensorCommand;
import com.iot.error404.chakiy.iot.infrastructure.persistence.jpa.repositories.TipoSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoSensorCommandServiceImpl {

    private final TipoSensorRepository tipoSensorRepository;

    @Autowired
    public TipoSensorCommandServiceImpl(TipoSensorRepository tipoSensorRepository) {
        this.tipoSensorRepository = tipoSensorRepository;
    }

    public TipoSensor createTipoSensor(CreateTipoSensorCommand command) {
        TipoSensor tipoSensor = new TipoSensor(
                command.name()
        );
        return tipoSensorRepository.save(tipoSensor);
    }
}