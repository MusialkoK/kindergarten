package pl.com.happyhouse.krzeptow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.happyhouse.krzeptow.model.WeeklyCarePlan;

@Repository
public interface WeeklyCarePlanRepository extends JpaRepository<WeeklyCarePlan, Long> {
}
