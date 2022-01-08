package com.hugolo.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "routineTracker")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineTracker {

    @Id
    @GeneratedValue
    private long id;
    private long routineId;
    private long day;
    private boolean success;
    private String comment;

    public RoutineTracker(long routineId, long day, boolean success, String comment) {
        this.routineId = routineId;
        this.day = day;
        this.success = success;
        this.comment = comment;
    }

}
