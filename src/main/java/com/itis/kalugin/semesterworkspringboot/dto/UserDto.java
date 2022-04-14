package com.itis.kalugin.semesterworkspringboot.dto;


import com.itis.kalugin.semesterworkspringboot.model.User;

public class UserDto {

    private int id;
    private String nickname;
    private String firstName;
    private String secondName;
    private String avatar;
    private String email;

    public UserDto(int id, String nickname, String firstName, String secondName, String avatar,
                   String email) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.secondName = secondName;
        this.avatar = avatar;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserDto fromModel(User user) {
        return new UserDto(user.getId(), user.getNickname(), user.getFirstName(), user.getSecondName(),
                user.getAvatar(), user.getEmail());
    }
}
