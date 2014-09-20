package io.leonid.springcash.service;

import io.leonid.springcash.model.Role;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by leonid on 20.09.14.
 */
public interface IRoleService extends IService<Role> {
    @Transactional
    Role findByName(String name);
}
