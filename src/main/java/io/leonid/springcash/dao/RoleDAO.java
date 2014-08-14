package io.leonid.springcash.dao;

import io.leonid.springcash.model.Role;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by leonid on 14.08.14.
 */
@Repository
public class RoleDAO extends GenericDAO<Role> {
    public Role findByName(String name) {
        if (name == null) {
            return null;
        }

        Query query = sessionFactory.getCurrentSession().createQuery("FROM Roles WHERE name=:name");
        query.setParameter("name", name);
        List<Role> listRole = query.list();

        if (listRole != null && !listRole.isEmpty()) {
            return listRole.get(0);
        }
        return null;
    }
}
