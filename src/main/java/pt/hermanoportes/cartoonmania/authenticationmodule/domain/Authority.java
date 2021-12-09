package pt.hermanoportes.cartoonmania.authenticationmodule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Authority implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Authority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
