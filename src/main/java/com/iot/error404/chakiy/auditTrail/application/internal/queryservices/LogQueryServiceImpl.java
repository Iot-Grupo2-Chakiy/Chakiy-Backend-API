package com.iot.error404.chakiy.auditTrail.application.internal.queryservices;

import com.iot.error404.chakiy.auditTrail.domain.model.aggregates.Log;
import com.iot.error404.chakiy.auditTrail.domain.model.queries.GetAllLogsQuery;
import com.iot.error404.chakiy.auditTrail.domain.model.queries.GetLogByIdQuery;
import com.iot.error404.chakiy.auditTrail.domain.services.LogQueryService;
import com.iot.error404.chakiy.auditTrail.infrastructure.persistence.jpa.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogQueryServiceImpl implements LogQueryService {
    @Autowired private LogRepository logRepository;

    @Override
    public List<Log> handle(GetAllLogsQuery query) {
        return logRepository.findAll();
    }

    @Override
    public Log handle(GetLogByIdQuery query) {
        return logRepository.findById(query.id())
                .orElseThrow(() -> new IllegalArgumentException("Log not found with id: " + query.id()));
    }
}
