package pl.com.happyhouse.krzeptow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.Presence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    Optional<Presence> getByChildAndDate(Child child, LocalDate date);

    @Query(value = "SELECT max(date) FROM Presence", nativeQuery = true)
    LocalDate getMaxDate();

    List<Presence> getByChild(Child c);

    List<Presence> getByChildAndDateAfter(Child child, LocalDate date);
}

