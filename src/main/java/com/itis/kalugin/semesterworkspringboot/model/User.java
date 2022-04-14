package com.itis.kalugin.semesterworkspringboot.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 5, max = 64, message = "Nickname should contain from 5 to 64 symbols")
    private String nickname;

    private String firstName;
    private String secondName;
    private String avatar;

    @Column(unique = true)
    private String email;

    @Size(min = 5, max = 64, message = "Password should contain from 5 to 64 symbols")
    @Column(nullable = false, length = 64)
    private String password;

    public User(int id, String nickname, String firstName, String secondName, String avatar,
                String email, String password) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.secondName = secondName;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
    }

    public User(String nickname, String firstName, String secondName, String avatar,
                String email, String password) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.secondName = secondName;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
