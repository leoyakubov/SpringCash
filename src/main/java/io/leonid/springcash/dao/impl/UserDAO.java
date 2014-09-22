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
        if (login == null) {
            return null;
        }

        Query query = sessionFactory.getCurrentSession().createQuery("FROM " + User.class.getName() + " WHERE login=:login");
        query.setParameter("login", login);
        List<User> listUser = query.list();

        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }
        return null;
    }
}
