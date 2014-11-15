package io.leonid.springcash.dao;

import io.leonid.springcash.model.User;

/**
 * Created by leonid on 20.09.14.
 */
public interface IUserDAO extends IDAO<User> {
    User findByLogin(String login);
    User findByEmail(String email);
}
