package com.iot.error404.chakiy.auditTrail.interfaces.REST;

import com.iot.error404.chakiy.auditTrail.domain.model.queries.GetAllLogsQuery;
import com.iot.error404.chakiy.auditTrail.domain.model.queries.GetLogByIdQuery;
import com.iot.error404.chakiy.auditTrail.domain.services.LogCommandService;
import com.iot.error404.chakiy.auditTrail.domain.services.LogQueryService;
import com.iot.error404.chakiy.auditTrail.interfaces.REST.resources.LogResource;
import com.iot.error404.chakiy.auditTrail.interfaces.REST.transform.LogResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/log")
public class LogController {
    @Autowired private LogQueryService logQueryService;

    @GetMapping
    public ResponseEntity<List<LogResource>> getAllLogs() {
        var query = new GetAllLogsQuery();
        var logResources = logQueryService.handle(query).stream().map(LogResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(logResources);
    }

    @GetMapping("{id}")
    public ResponseEntity<LogResource> getLogById(@PathVariable Long id) {
        var query = new GetLogByIdQuery(id);
        var log = logQueryService.handle(query);
        var logResource = LogResourceFromEntityAssembler.toResourceFromEntity(log);
        return ResponseEntity.ok(logResource);
    }
}
