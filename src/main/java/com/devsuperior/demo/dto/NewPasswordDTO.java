package com.devsuperior.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPasswordDTO {

    private String token;
    private String newPassword;

    public NewPasswordDTO(){
    }

    public NewPasswordDTO(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }
}
