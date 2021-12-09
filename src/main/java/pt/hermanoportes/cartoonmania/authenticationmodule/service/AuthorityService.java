package pt.hermanoportes.cartoonmania.authenticationmodule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.ApplicationUser;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.Authority;
import pt.hermanoportes.cartoonmania.authenticationmodule.repository.ApplicationUserRepository;
import pt.hermanoportes.cartoonmania.authenticationmodule.repository.AuthorityRepository;

import javax.persistence.NoResultException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final ApplicationUserRepository userRepository;
    private final Set<Authority> authorities = new HashSet<>();

    public void grantAuthorities(String username, String authority) {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: '" + username + "'"));

        Authority byName = authorityRepository.findByName(authority).orElseThrow(
                () -> new NoResultException("Authority not found"));

        this.authorities.add(byName);
        user.setAuthorities(this.authorities);

        userRepository.save(user);
    }
}
