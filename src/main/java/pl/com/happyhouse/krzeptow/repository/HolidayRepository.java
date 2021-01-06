package pl.com.happyhouse.krzeptow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.happyhouse.krzeptow.model.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> getByDateBetween(LocalDate start, LocalDate end);
}
