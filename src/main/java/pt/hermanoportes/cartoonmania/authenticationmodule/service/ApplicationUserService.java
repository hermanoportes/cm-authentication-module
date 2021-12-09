package pt.hermanoportes.cartoonmania.authenticationmodule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.ApplicationUser;
import pt.hermanoportes.cartoonmania.authenticationmodule.repository.ApplicationUserRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ApplicationUserService {
    private final ApplicationUserRepository userRepository;

    public ApplicationUser getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
