package project.cinema.model.dto;

import project.cinema.annotation.EmailValidation;
import project.cinema.annotation.PasswordValidation;

@PasswordValidation(field = "password", fieldMatch = "repeatedPassword")
public class UserRequestDto {
    @EmailValidation
    private String email;
    private String password;
    private String repeatedPassword;

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

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
