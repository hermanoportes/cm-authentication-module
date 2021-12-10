package pt.hermanoportes.cartoonmania.authenticationmodule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.Authority;
import pt.hermanoportes.cartoonmania.authenticationmodule.repository.AuthorityRepository;

import javax.persistence.NoResultException;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public Authority findByName(String name) {
        return authorityRepository.findByName(name).orElseThrow(
                () -> new NoResultException("Authority not found: " + name));
    }
}
