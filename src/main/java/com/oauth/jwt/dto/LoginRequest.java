package com.oauth.jwt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class LoginRequest {

    @NotBlank
    @Pattern(regexp = "^.*([A-Z]{1})?.*([a-z]+)?.*(\\d+)?@(\\w+).(\\w{2,})$",message = "Formato de correo no corresponde")
    private  String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]{1})(?=.*\\d{2})([A-Za-z\\d]){4,}$",message = "Formato de password incorrecto, debe ingresar al menos una mayuscula dos numeros")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
