package com.iot.error404.chakiy.auditTrail.domain.services;

import com.iot.error404.chakiy.auditTrail.domain.model.aggregates.Log;
import com.iot.error404.chakiy.auditTrail.domain.model.queries.GetAllLogsQuery;
import com.iot.error404.chakiy.auditTrail.domain.model.queries.GetLogByIdQuery;

import java.util.List;

public interface LogQueryService {
    List<Log> handle(GetAllLogsQuery query);
    Log handle(GetLogByIdQuery query);
}
