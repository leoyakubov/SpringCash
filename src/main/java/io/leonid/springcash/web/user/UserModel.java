package io.leonid.springcash.web.user;

import io.leonid.springcash.model.Role;
import io.leonid.springcash.model.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by leonid on 22.09.14.
 */
public class UserModel {
    private long id;

    @NotBlank(message = "{valid.login.notempty}")
    @Size(min=3, max=30, message = "{valid.login.size}")
    private String login;

    @NotBlank(message = "{valid.password.notempty}")
    @Size(min=6, max=60, message = "{valid.password.size}")
    private String password;

    @NotBlank(message = "{valid.password.conf.notempty}")
    @Size(min=6, max=60, message = "{valid.password.size}")
    private String confirmedPassword;

    @NotBlank(message = "{valid.email.notempty}")
    @Email(message = "{valid.email.size}")
    private String email;

    private boolean isActive = true;

    @NotNull(message = "{valid.role.select}")
    private Role role;

    private String firstName;

    private String lastName;

    public UserModel() {}

    public UserModel(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.confirmedPassword = user.getPassword();
        this.email = user.getEmail();
        this.isActive = user.isActive();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public User constructUserFromModel() {
        User user = new User();
        user.setId(this.id);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setActive(this.isActive);
        user.setRole(this.role);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);

        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
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
