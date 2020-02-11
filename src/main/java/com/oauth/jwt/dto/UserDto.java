package com.oauth.jwt.dto;

import com.oauth.jwt.model.ContactInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;


public class UserDto {
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(max = 40)
    @Pattern(regexp = "^.*([A-Z]{1})?.*([a-z]+)?.*(\\d+)?@(\\w+).(\\w{2,})$",message = "Formato de correo no corresponde")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]{1})(?=.*\\d{2})([A-Za-z\\d]){4,}$",message = "Formato de password incorrecto, debe ingresar al menos una mayuscula dos numeros")
    private String password;

    private Set<ContactInfo> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Set<ContactInfo> getPhones() {
        return phones;
    }

    public void setPhones(Set<ContactInfo> phones) {
        this.phones = phones;
    }
}
