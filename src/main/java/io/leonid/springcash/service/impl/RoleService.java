package io.leonid.springcash.service.impl;

import io.leonid.springcash.dao.impl.RoleDAO;
import io.leonid.springcash.model.Role;
import io.leonid.springcash.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by leonid on 14.08.14.
 */
@Service
public class RoleService extends GenericService<Role> implements IRoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }
}
