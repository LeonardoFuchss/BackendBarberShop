package com.backend.reservetion.Barbershop.controllers;

import com.backend.reservetion.Barbershop.dtos.ActivityDTOs;
import com.backend.reservetion.Barbershop.domain.Activity;
import com.backend.reservetion.Barbershop.exceptions.ActivityException;
import com.backend.reservetion.Barbershop.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping
    public ResponseEntity<Activity> newActivity(@Valid @RequestBody ActivityDTOs activityDTOs) throws ActivityException {
            Activity newActivity = activityService.createdActivity(activityDTOs);
            return new ResponseEntity<>(newActivity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAll() throws ActivityException {
            List<Activity> allActivities = activityService.getAll();
            return ResponseEntity.ok(allActivities);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Activity> activityById(@PathVariable Long id) throws ActivityException {
            Activity activityById = activityService.activityById(id);
            return ResponseEntity.ok(activityById);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Activity> update(@PathVariable Long id, @RequestBody ActivityDTOs activityDTOs) throws ActivityException {
            Activity newActivity = activityService.update(id, activityDTOs);
            return ResponseEntity.ok().body(newActivity);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) throws ActivityException {
            activityService.deleteById(id);


    }
}
