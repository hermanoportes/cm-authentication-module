package pt.hermanoportes.cartoonmania.authenticationmodule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pt.hermanoportes.cartoonmania.authenticationmodule.controller.form.LoginForm;
import pt.hermanoportes.cartoonmania.authenticationmodule.controller.dto.TokenDto;
import pt.hermanoportes.cartoonmania.authenticationmodule.service.TokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginForm loginForm) {
        try {
            Authentication authentication = authManager.authenticate(loginForm.toUsernamePasswordAuthenticationToken());
            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Void> xpto() {
        return ResponseEntity.ok().build();
    }
}
