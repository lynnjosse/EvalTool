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
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    //primitive boolean defaults to false//
    private boolean admin;

    @ManyToMany(mappedBy = "users")
    private List<Building> buildings;

    public User() {}

    public User(String password, String email, String firstName, String lastName) {

        this.pwHash = hashPassword(password);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {  this.firstName = firstName;  }

    public String getLastName() { return lastName;}

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email;}

    private static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public boolean isAdmin() {
        return admin;
    }

    public List<Building> getBuildings() { return buildings; }


}

