package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Child;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.repository.ChildRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final UserService userService;

    public Child save(Child child){
        return childRepository.save(child);
    }

    public Child getById(long id){
        return childRepository.getById(id);
    }

    public List<Child> getByGuardianId(long id){
        User user = userService.getById(id);
        return childRepository.getChildrenByGuardiansContaining(user);
    }
}
