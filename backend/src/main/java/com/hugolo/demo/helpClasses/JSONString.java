package com.hugolo.demo.helpClasses;

import java.util.List;

public class JSONString {

    public String json(List<RoutineStat> routineStats) {

        String json = "[";
        for(int i = 0; i < routineStats.size(); i++) {
            RoutineStat r = routineStats.get(i);
            json += "{\"name\": \"" +  r.getName() + "\", " +
                    "\"daysPerWeek\": " + r.getDaysPerWeek() + ", " +
                    "\"daysPerMonth\": " + r.getDaysPerMonth() + ", " +
                    "\"routineTrackerStat\": " + "[";
            for(int ii = 0; ii < r.getRoutineTrackerStats().size(); ii++) {
                RoutineTrackerStat rt = r.getRoutineTrackerStats().get(ii);
                json += "{\"day\": " + rt.getDay() + ", " +
                        "\"isSuccess\": " + rt.isSuccess() + ", " +
                        "\"comment\": \"" + rt.getComment() + "\"}" +
                        (ii == r.getRoutineTrackerStats().size() - 1 ? "" : ", ");
            }
            json += "]}" + (i == routineStats.size() - 1 ? "" : ", ");
        }
        return json + "]";
    }
}
