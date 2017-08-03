package com.lynnjosse.EvalTool.models;

import com.sun.javafx.beans.IDProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;


    @NotNull
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,20}", message = "Invalid username")
    private String username;

    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public User() {}

    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.pwHash = hashPassword(password);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {  this.firstName = firstName;  }

    public String getLastName() { return lastName;}

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() {
        return username;
    }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email;}

    private static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
