package io.leonid.springcash.service;

import io.leonid.springcash.dao.RoleDAO;
import io.leonid.springcash.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by leonid on 14.08.14.
 */
@Service
public class RoleService extends GenericService<Role> {
    @Autowired
    private RoleDAO roleDAO;

    @Transactional
    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }
}
