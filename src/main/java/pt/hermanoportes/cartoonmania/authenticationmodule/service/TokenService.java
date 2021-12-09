package pt.hermanoportes.cartoonmania.authenticationmodule.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.ApplicationUser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Log4j2
public class TokenService {

    @Value(value = "${cartoon-mania.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {

        ApplicationUser loggedUser = (ApplicationUser) authentication.getPrincipal();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plus(60, ChronoUnit.MINUTES);

        return Jwts.builder()
                .setIssuer("Cartoon Mania Api")
                .setSubject(loggedUser.getId().toString())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
