package pl.com.happyhouse.krzeptow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.happyhouse.krzeptow.model.Role;
import pl.com.happyhouse.krzeptow.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(Role role);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
