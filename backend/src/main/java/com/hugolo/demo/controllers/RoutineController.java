package com.hugolo.demo.controllers;

import com.hugolo.demo.entities.Routine;
import com.hugolo.demo.services.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/routine")
public class RoutineController {
    @Autowired
    private RoutineService routineService;

    @GetMapping
    public List<Routine> getAllRoutines() {
        return routineService.getAllRoutines();
    }
}
