package pl.com.happyhouse.krzeptow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.happyhouse.krzeptow.model.MealChange;

import java.util.Optional;

@Repository
public interface MealChangeRepository extends JpaRepository<MealChange, Long> {

    Optional<MealChange> getById(Long id);
}
