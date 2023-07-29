package dev.arunava.taskmanager.Task.Manager.service;

import dev.arunava.taskmanager.Task.Manager.model.SecurityUser;
import dev.arunava.taskmanager.Task.Manager.model.User;
import dev.arunava.taskmanager.Task.Manager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implements the UserDetailsService for User
 */
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("Email not found: " + email);
        }
        return new SecurityUser(user.get());
    }
}
