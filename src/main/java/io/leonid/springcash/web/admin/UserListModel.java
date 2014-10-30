package io.leonid.springcash.web.admin;

import io.leonid.springcash.model.User;

import java.util.ArrayList;
import java.util.List;

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

    private List<UserListItem> userListItems = new ArrayList();

    public List<UserListItem> getUserListItems() {
        return userListItems;
    }

    public void setUserListItems(List<UserListItem> userListItems) {
        this.userListItems = userListItems;
    }

    public UserListModel(List<User> users) {
        createListItemsFromUsers(users);
    }

    private void createListItemsFromUsers(List<User> users) {
        for (User user : users) {
            UserListItem item = new UserListItem();
            item.setId(user.getId());
            item.setLogin(user.getLogin());
            item.setPassword(user.getPassword());
            item.setEmail(user.getEmail());
            item.setActive(user.isActive());
            item.setRole(user.getRole());
            item.setFirstName(user.getFirstName());
            item.setLastName(user.getLastName());
            userListItems.add(item);
        }
    }
}
