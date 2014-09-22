package io.leonid.springcash.dao.impl;

import io.leonid.springcash.dao.IRoleDAO;
import io.leonid.springcash.model.Role;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by leonid on 14.08.14.
 */
@Repository
public class RoleDAO extends GenericDAO<Role> implements IRoleDAO {
    public RoleDAO() {
        super(Role.class);
    }

    public RoleDAO(Class<Role> persistentClass) {
        super(persistentClass);
    }

    @Override
    public Role findByName(String name) {
        if (name == null) {
            return null;
        }

        Query query = sessionFactory.getCurrentSession().createQuery("FROM " + Role.class.getName() + " WHERE name=:name");
        query.setParameter("name", name);
        List<Role> listRole = query.list();

        if (listRole != null && !listRole.isEmpty()) {
            return listRole.get(0);
        }
        return null;
    }
}
