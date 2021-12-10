package pt.hermanoportes.cartoonmania.authenticationmodule.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import pt.hermanoportes.cartoonmania.authenticationmodule.domain.ApplicationUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class ApplicationUserSignupForm {

    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty @Min(6)
    private String password;

    public ApplicationUserSignupForm(ApplicationUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public ApplicationUser toApplicationUser() {
        ApplicationUser user = new ApplicationUser();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(this.password));
        return user;
    }
}
