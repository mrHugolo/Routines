package com.hugolo.demo.services;

import com.hugolo.demo.entities.Routine;
import com.hugolo.demo.entities.RoutineTracker;
import com.hugolo.demo.helpClasses.JSONString;
import com.hugolo.demo.helpClasses.RoutineStat;
import com.hugolo.demo.helpClasses.RoutineTrackerStat;
import com.hugolo.demo.repositories.RoutineRepository;
import com.hugolo.demo.repositories.RoutineTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public String getAllRoutineTrackersXDaysBack(int daysBack) {
        Calendar date = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        long today = date.getTimeInMillis();
        date.add(Calendar.DAY_OF_MONTH, -daysBack);
        long lastDay = date.getTimeInMillis();

        List<Routine> routines = routineRepository.findAll();
        List<RoutineTracker> routineTrackers = routineTrackerRepository.getAllByDayBetween(lastDay, today);
        List<RoutineStat> routineStats = new ArrayList<>();

        for(int i = 0; i < routines.size(); i++) {
            Routine r = routines.get(i);
            List<RoutineTrackerStat> routineTrackerStats = new ArrayList<>();
            routineTrackers.stream().parallel().filter(rt -> rt.getRoutineId() == r.getId()).collect(Collectors.toList()).forEach(it ->{
                routineTrackerStats.add(new RoutineTrackerStat(it.getDay(), it.isSuccess(), it.getComment()));
            });
            routineStats.add(new RoutineStat(r.getName(), r.getDaysPerWeek(), r.getDaysPerMonth(), routineTrackerStats));
        }
        JSONString jsonString = new JSONString();
        return jsonString.json(routineStats);
    }
}
