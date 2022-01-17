package com.hugolo.demo.controllers;

import com.hugolo.demo.entities.RoutineTracker;
import com.hugolo.demo.helpClasses.RoutineStat;
import com.hugolo.demo.services.RoutineTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/routineTracker")
public class RoutineTrackerController {
    @Autowired
    private RoutineTrackerService routineTrackerService;

    @GetMapping("/{day}")
    public String getAllRoutineTrackersPerDay(@PathVariable long day) {
        return routineTrackerService.getAllRoutineTrackersPerDay(day);
    }

    @GetMapping("/stat/{daysBack}")
    public String getAllRoutineTrackersXDaysBack(@PathVariable int daysBack) {
        return routineTrackerService.getAllRoutineTrackersXDaysBack(daysBack);
    }

    @PostMapping
    public RoutineTracker createRoutineTracker(@RequestBody RoutineTracker routineTracker) {
        return routineTrackerService.createRoutineTracker(routineTracker);
    }
}
