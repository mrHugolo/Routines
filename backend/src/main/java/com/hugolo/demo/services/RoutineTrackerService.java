package com.hugolo.demo.services;

import com.hugolo.demo.entities.Routine;
import com.hugolo.demo.entities.RoutineTracker;
import com.hugolo.demo.repositories.RoutineRepository;
import com.hugolo.demo.repositories.RoutineTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineTrackerService {

    @Autowired
    RoutineTrackerRepository routineTrackerRepository;
    @Autowired
    RoutineRepository routineRepository;

    public String getAllRoutineTrackersPerDay(long day) {
        List<RoutineTracker> routineTrackers = routineTrackerRepository.findAllByDay(day);
        List<Routine> routines = routineRepository.findAll();
        String json = "[";
        for(int i = 0; i < routines.size(); i++) {
            Routine r = routines.get(i);
            List<RoutineTracker> tracker = routineTrackers.stream().parallel().filter(rt -> rt.getRoutineId() == r.getId()).collect(Collectors.toList());
            json += routines.get(i).populate(tracker.size() == 1 ? tracker.get(0) : null) + (i < routines.size() - 1 ? ", " : "");
        }
        return json + "]";
    }

    public RoutineTracker createRoutineTracker(RoutineTracker routineTracker) {
        return routineTrackerRepository.save(routineTracker);
    }
}
