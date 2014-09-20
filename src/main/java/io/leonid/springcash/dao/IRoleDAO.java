package io.leonid.springcash.dao;

import io.leonid.springcash.model.Role;

/**
 * Created by leonid on 20.09.14.
 */
public interface IRoleDAO extends IDAO<Role> {
    Role findByName(String name);
}
