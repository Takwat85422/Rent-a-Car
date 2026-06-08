package ro.unibuc.rentacar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.unibuc.rentacar.entity.Utilizator;
import ro.unibuc.rentacar.repository.UtilizatorRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedUsers(UtilizatorRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                Utilizator admin = new Utilizator();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRol("ADMIN");
                repo.save(admin);

                Utilizator user = new Utilizator();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRol("USER");
                repo.save(user);
            }
        };
    }
}