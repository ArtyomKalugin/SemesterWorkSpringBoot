package com.itis.kalugin.semesterworkspringboot.dto;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Nickname shouldn't be blank!")
    private String nickname;

    @NotBlank(message = "First name shouldn't be blank!")
    private String firstName;

    @NotBlank(message = "Second name shouldn't be blank!")
    private String secondName;

    @NotBlank(message = "Email shouldn't be blank!")
    private String email;

    @NotBlank(message = "Password shouldn't be blank!")
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
}
