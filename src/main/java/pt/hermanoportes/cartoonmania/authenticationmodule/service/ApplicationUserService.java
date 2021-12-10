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
    private final AuthorityService authorityService;

    public ApplicationUser save(ApplicationUser user) {
        user.getAuthorities().add(authorityService.findByName("ROLE_USER"));
        return userRepository.save(user);
    }

    public ApplicationUser getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    public ApplicationUser findByUsername(String username) {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found: '" + username + "'"));
//    }
//
//    public void grantAuthority(String username, String name) {
//        ApplicationUser user = findByUsername(username);
//        Authority authority = authorityService.findByName(name);
//        user.getAuthorities().add(authority);
//        userRepository.save(user);
//    }
}
