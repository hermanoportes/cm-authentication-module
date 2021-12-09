package pt.hermanoportes.cartoonmania.authenticationmodule.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.ApplicationUser;
import pt.hermanoportes.cartoonmania.authenticationmodule.service.ApplicationUserService;
import pt.hermanoportes.cartoonmania.authenticationmodule.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final ApplicationUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getIncomeToken(request);

        if(tokenService.isTokenValid(token)) {
            authenticate(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        Long userId = tokenService.getUserId(token);
        ApplicationUser user = userService.getById(userId);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getIncomeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer")) {
            return  null;
        }
        return authorization.split(" ")[1];
    }
}
