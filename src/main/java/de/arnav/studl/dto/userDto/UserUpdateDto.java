package de.arnav.studl.dto.userDto;

import org.springframework.stereotype.Component;

@Component
public class UserUpdateDto {

    private String name;
    private String email;
    private String oldPassword;
    private String newPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
