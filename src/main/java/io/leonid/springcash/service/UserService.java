package io.leonid.springcash.service;

import io.leonid.springcash.dao.UserDAO;
import io.leonid.springcash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by leonid on 14.08.14.
 */
@Service
public class UserService extends GenericService<User> {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }
}
