package pl.com.happyhouse.krzeptow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.Role;
import pl.com.happyhouse.krzeptow.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getByName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
