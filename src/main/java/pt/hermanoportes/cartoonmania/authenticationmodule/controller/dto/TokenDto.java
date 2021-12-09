package pt.hermanoportes.cartoonmania.authenticationmodule.controller.dto;

import lombok.Getter;

@Getter
public class TokenDto {

    private final String token;
    private final String type;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
