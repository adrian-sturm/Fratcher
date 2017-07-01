package com.asturm.webengineering_2017.fratcher.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A user of the application
 */
@Entity(name = "User_")
public class User {

    public static final int EMAIL_LENGTH = 1024;

    /**
     * The id of this user
     */
    @Id
    @JsonIgnore
    @GeneratedValue
    private Long id;

    /**
     * The email address of this user
     */
    @Column(length = EMAIL_LENGTH, unique = true)
    private String email;

    /**
     * This users password
     */
    @JsonIgnore
    private String password;

    /**
     * The description text for this user
     */
    @Column(length = 2 * EMAIL_LENGTH)
    private String descriptionText;

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
