package com.christian.diams.model;

import com.christian.diams.utility.Constants;

import javax.persistence.*;

@Table
@Entity
@NamedQueries(@NamedQuery(name="User.getAll", query="SELECT u FROM User u"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(length = Constants.NAME_LENGTH)
    private String firstName;

    @Column(length = Constants.NAME_LENGTH)
    private String lastName;

    @Column(length = Constants.EMAIL_LENGTH)
    private String email;

    @Column(length = Constants.PASSWORD_LENGTH)
    private String password;

    public User() {
    }

    public User(Long userID, String firstName, String lastName, String email, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
