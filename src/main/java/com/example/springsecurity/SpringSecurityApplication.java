package com.example.springsecurity;

import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class SpringSecurityApplication  {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SpringSecurityApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                User user = new User("safa", passwordEncoder.encode("safa"));
                user.setRoleList(Arrays.asList(new Role("admin")));
                userRepository.save(user);
            }
        };
    }

}
