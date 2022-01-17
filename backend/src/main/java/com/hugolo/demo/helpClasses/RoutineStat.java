package com.hugolo.demo.helpClasses;

import java.util.List;

public class RoutineStat {
    private String name;
    private int daysPerWeek;
    private int daysPerMonth;
    private List<RoutineTrackerStat> routineTrackerStats;

    public RoutineStat(String name, int daysPerWeek, int daysPerMonth, List<RoutineTrackerStat> routineTrackerStats) {
        this.name = name;
        this.daysPerWeek = daysPerWeek;
        this.daysPerMonth = daysPerMonth;
        this.routineTrackerStats = routineTrackerStats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public int getDaysPerMonth() {
        return daysPerMonth;
    }

    public void setDaysPerMonth(int daysPerMonth) {
        this.daysPerMonth = daysPerMonth;
    }

    public List<RoutineTrackerStat> getRoutineTrackerStats() {
        return routineTrackerStats;
    }

    public void setRoutineTrackerStats(List<RoutineTrackerStat> routineTrackerStats) {
        this.routineTrackerStats = routineTrackerStats;
    }
}
