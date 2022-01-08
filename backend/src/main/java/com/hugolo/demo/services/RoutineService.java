package com.hugolo.demo.services;

import com.hugolo.demo.entities.Routine;
import com.hugolo.demo.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineService {

    @Autowired
    RoutineRepository routineRepository;

    public List<Routine> getAllRoutines() { return routineRepository.findAll();}

}
