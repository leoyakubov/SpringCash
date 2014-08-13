package io.leonid.springcash.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by leonid on 13.08.14.
 */
@Entity
@Table(name = "Users")
public class User extends BaseEntity{
    @Column(name = "login", nullable = false, unique = true)
    @NotEmpty
    private String login;

    @Column(name = "password", nullable = false, unique = false)
    @NotEmpty
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "isActive", nullable = false, unique = false)
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "firstName", nullable = false, unique = false)
    private String firstName;

    @Column(name = "lastName", nullable = false, unique = false)
    private String lastName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
