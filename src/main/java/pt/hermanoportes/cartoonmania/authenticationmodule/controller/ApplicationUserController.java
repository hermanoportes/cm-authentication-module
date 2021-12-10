package pt.hermanoportes.cartoonmania.authenticationmodule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.hermanoportes.cartoonmania.authenticationmodule.controller.form.ApplicationUserSignupForm;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.ApplicationUser;
import pt.hermanoportes.cartoonmania.authenticationmodule.service.ApplicationUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;

    @PostMapping("/signup")
    public ResponseEntity<ApplicationUserSignupForm> signup(@RequestBody @Valid ApplicationUserSignupForm signupForm) {
        ApplicationUser saved = applicationUserService.save(signupForm.toApplicationUser());
        return new ResponseEntity<>(new ApplicationUserSignupForm(saved), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApplicationUserSignupForm> getById(@PathVariable long id) {
        ApplicationUser user = applicationUserService.getById(id);
        return ResponseEntity.ok(new ApplicationUserSignupForm(user));
    }
}
