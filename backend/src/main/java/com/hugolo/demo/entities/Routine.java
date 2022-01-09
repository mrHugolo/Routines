package com.hugolo.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "routine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private long sectionId;
    private int daysPerWeek;
    private int daysPerMonth;
    private int priority;

    public Routine(String name, long sectionId, int daysPerWeek, int daysPerMonth, int priority) {
        this.name = name;
        this.sectionId = sectionId;
        this.daysPerWeek = daysPerWeek;
        this.daysPerMonth = daysPerMonth;
        this.priority = priority;
    }

    public String populate(RoutineTracker rt) {
        String rtString = rt != null ?
                ", \"comment\": \"" + rt.getComment() + "\"" +
                ", \"success\": " + rt.isSuccess()
                : "";
        return  "{" +
                "\"id\": " + id +
                ", \"name\": \"" + name + "\"" +
                ", \"sectionId\": " + sectionId +
                ", \"daysPerWeek\": " + daysPerWeek +
                ", \"daysPerMonth\": " + daysPerMonth +
                ", \"priority\": " + priority +
                rtString +
                "}";
    }
}
