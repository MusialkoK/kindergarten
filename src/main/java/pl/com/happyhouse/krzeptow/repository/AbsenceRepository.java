package pl.com.happyhouse.krzeptow.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.happyhouse.krzeptow.model.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {


}
