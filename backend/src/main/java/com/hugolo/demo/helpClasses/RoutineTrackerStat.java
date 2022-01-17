package com.hugolo.demo.helpClasses;

public class RoutineTrackerStat {
    private long day;
    private boolean isSuccess;
    private String comment;

    public RoutineTrackerStat(long day, boolean isSuccess, String comment) {
        this.day = day;
        this.isSuccess = isSuccess;
        this.comment = comment;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
