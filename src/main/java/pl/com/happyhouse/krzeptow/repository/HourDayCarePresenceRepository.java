package pl.com.happyhouse.krzeptow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.HourDayCarePresence;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HourDayCarePresenceRepository extends JpaRepository<HourDayCarePresence, Long> {

    List<HourDayCarePresence> getHourDayCarePresencesByChildAndDateBetween(Child child, LocalDate start, LocalDate end);

}
