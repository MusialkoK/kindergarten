package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User getById(long id){
        return userRepository.findById(id).orElseThrow();
    }

}
