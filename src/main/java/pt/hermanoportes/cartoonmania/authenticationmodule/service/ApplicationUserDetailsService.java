package pt.hermanoportes.cartoonmania.authenticationmodule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pt.hermanoportes.cartoonmania.authenticationmodule.repository.ApplicationUserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ApplicationUserDetailsService implements UserDetailsService {
    private final ApplicationUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }
}
