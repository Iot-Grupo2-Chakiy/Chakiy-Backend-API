package com.iot.error404.chakiy.routines.interfaces.REST;

import com.iot.error404.chakiy.routines.domain.model.commands.DeleteRoutineCommand;
import com.iot.error404.chakiy.routines.domain.model.queries.GetAllRoutinesQuery;
import com.iot.error404.chakiy.routines.domain.model.queries.GetRoutineByIdQuery;
import com.iot.error404.chakiy.routines.domain.services.RoutineCommandService;
import com.iot.error404.chakiy.routines.domain.services.RoutineQueryService;
import com.iot.error404.chakiy.routines.interfaces.REST.resources.CreateRoutineResource;
import com.iot.error404.chakiy.routines.interfaces.REST.resources.RoutineResource;
import com.iot.error404.chakiy.routines.interfaces.REST.resources.UpdateRoutineResource;
import com.iot.error404.chakiy.routines.interfaces.REST.transform.CreateRoutineCommandFromResourceAssembler;
import com.iot.error404.chakiy.routines.interfaces.REST.transform.RoutineResourceFromEntityAssembler;
import com.iot.error404.chakiy.routines.interfaces.REST.transform.UpdateRoutineCommandFromResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/routine")
public class RoutineController {
    @Autowired private RoutineCommandService routineCommandService;
    @Autowired private RoutineQueryService routineQueryService;

    @GetMapping
    public ResponseEntity<List<RoutineResource>> getAllRoutines(){
        var query = new GetAllRoutinesQuery();
        var routineResources = routineQueryService.handle(query).stream()
                .map(RoutineResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(routineResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineResource> getRoutineById(@PathVariable Long id) {
        var query = new GetRoutineByIdQuery(id);
        var routine = routineQueryService.handle(query);
        var routineResource = RoutineResourceFromEntityAssembler.toResourceFromEntity(routine);
        return ResponseEntity.ok(routineResource);
    }

    @PostMapping
    public ResponseEntity<RoutineResource> createRoutine(@RequestBody CreateRoutineResource resource) {
        var command = CreateRoutineCommandFromResourceAssembler.toCommandFromResource(resource);
        var routine = routineCommandService.handle(command);
        if (routine.isEmpty()) return ResponseEntity.badRequest().build();
        var routineResource = RoutineResourceFromEntityAssembler.toResourceFromEntity(routine.get());
        return ResponseEntity.created(URI.create("/routine/" + routine.get().getId()))
                .body(routineResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineResource> updateRoutine(@PathVariable Long id, @RequestBody UpdateRoutineResource resource) {
        var command = UpdateRoutineCommandFromResourceAssembler.toCommandFromResource(resource);
        var routine = routineCommandService.handle(id, command);
        if (routine.isEmpty()) return ResponseEntity.badRequest().build();
        var routineResource = RoutineResourceFromEntityAssembler.toResourceFromEntity(routine.get());
        return ResponseEntity.ok(routineResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        var command = new DeleteRoutineCommand(id);
        routineCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
