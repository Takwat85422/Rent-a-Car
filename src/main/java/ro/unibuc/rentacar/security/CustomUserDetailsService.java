package ro.unibuc.rentacar.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.Utilizator;
import ro.unibuc.rentacar.repository.UtilizatorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilizatorRepository repo;

    public CustomUserDetailsService(UtilizatorRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Utilizator u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizator inexistent: " + username));
        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRol())          // adauga automat prefixul ROLE_
                .disabled(!u.isEnabled())
                .build();
    }
}