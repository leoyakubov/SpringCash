package io.leonid.springcash.dao.impl;

import io.leonid.springcash.dao.IUserDAO;
import io.leonid.springcash.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by leonid on 13.08.14.
 */
@Repository
public class UserDAO extends GenericDAO<User> implements IUserDAO {
    public UserDAO() {
        super(User.class);
    }

    public UserDAO(Class<User> persistentClass) {
        super(persistentClass);
    }

    @Override
    public User findByLogin(String login) {
        if (login == null && login.isEmpty()) {
            return null;
        }

        Query query = sessionFactory.getCurrentSession().createQuery("FROM " + User.class.getName() + " WHERE login=:login");
        query.setParameter("login", login);
        List<User> userList = query.list();

        if (userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        if (email == null && email.isEmpty()) {
            return null;
        }

        Query query = sessionFactory.getCurrentSession().createQuery("FROM " + User.class.getName() + " WHERE email=:email");
        query.setParameter("email", email);
        List<User> userList = query.list();

        if (userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }

        return null;
    }
}
