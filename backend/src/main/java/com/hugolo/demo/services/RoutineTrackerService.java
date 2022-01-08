package com.hugolo.demo.services;

import com.hugolo.demo.entities.RoutineTracker;
import com.hugolo.demo.repositories.RoutineTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineTrackerService {

    @Autowired
    RoutineTrackerRepository routineTrackerRepository;

    public List<RoutineTracker> getAllRoutineTrackers() { return routineTrackerRepository.findAll();}

}
