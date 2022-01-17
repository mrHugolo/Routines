package com.hugolo.demo.repositories;

import com.hugolo.demo.entities.RoutineTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineTrackerRepository extends JpaRepository<RoutineTracker, Long> {
    List<RoutineTracker> findAllByDay(long day);

    RoutineTracker save(RoutineTracker routineTracker);

    @Query(value = "SELECT * FROM routine_tracker", nativeQuery = true)
    List<RoutineTracker> getAllRoutineTrackersXDaysBack(long today, long lastDay);

    List<RoutineTracker> getAllByDayBetween(long lastDay, long today);
}
