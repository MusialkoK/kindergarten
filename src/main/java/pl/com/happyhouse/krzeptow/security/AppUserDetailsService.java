package pl.com.happyhouse.krzeptow.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.happyhouse.krzeptow.model.User;
import pl.com.happyhouse.krzeptow.repository.UserRepository;

@AllArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).get();
        return new AppUserDetails(user);
    }


}
