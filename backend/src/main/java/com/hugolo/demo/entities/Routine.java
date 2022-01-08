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

    public Routine(String name, long sectionId, int daysPerWeek, int daysPerMonth) {
        this.name = name;
        this.sectionId = sectionId;
        this.daysPerWeek = daysPerWeek;
        this.daysPerMonth = daysPerMonth;
    }

}
