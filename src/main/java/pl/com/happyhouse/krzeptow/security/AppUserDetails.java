package pl.com.happyhouse.krzeptow.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class AppUserDetails implements UserDetails {

//    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return null;
    }

    @Override
    public String getPassword() {
//        return user.getPassword();
        return null;
    }

    @Override
    public String getUsername() {
//        return user.getEmail();
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
