package io.leonid.springcash.service;

import io.leonid.springcash.model.User;

/**
 * Created by leonid on 20.09.14.
 */
public interface IUserService extends IService<User> {
    User findByLogin(String login);
}
