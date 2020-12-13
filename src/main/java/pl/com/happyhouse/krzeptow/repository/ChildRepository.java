package pl.com.happyhouse.krzeptow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.User;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    Child getById(long id);
    List<Child> getChildrenByGuardiansContaining(User user);
}
