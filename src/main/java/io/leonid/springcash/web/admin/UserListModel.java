package io.leonid.springcash.web.admin;

import io.leonid.springcash.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leonid on 15.10.14.
 */
public class UserListModel {
    //TODO:
    /* -add map - changed entities (save only the into db)
     * -add validator and validation in controller
     * -delete entities from db if user clicks DELETE
     * -add multiselection option and multiuser action buttons
    * */

    private List<User> users = new ArrayList<>();
    private Map<Integer, User> updatedUsers = new HashMap<>();

    public UserListModel(List<User> users) {
            this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<Integer, User> getUpdatedUsers() {
        return updatedUsers;
    }

    public void setUpdatedUsers(Map<Integer, User> updatedUsers) {
        this.updatedUsers = updatedUsers;
    }
}
