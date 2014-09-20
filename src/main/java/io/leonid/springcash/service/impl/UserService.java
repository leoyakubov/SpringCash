package io.leonid.springcash.service.impl;

import io.leonid.springcash.dao.impl.UserDAO;
import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by leonid on 14.08.14.
 */
@Service
public class UserService extends GenericService<User> implements IUserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }
}
