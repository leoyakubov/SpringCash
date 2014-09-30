package io.leonid.springcash.service.impl;

import io.leonid.springcash.dao.impl.UserDAO;
import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by leonid on 14.08.14.
 */
@Service
public class UserService extends GenericService<User> implements IUserService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Transactional
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        io.leonid.springcash.model.User user = userDAO.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

        return buildUserForAuthentication(user, authorities);
    }

    // Converts io.leonid.springcash.model.User user to
    // org.springframework.security.core.userdetails.User
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(io.leonid.springcash.model.User user,
                                                                                          List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), user.isActive(),
                true, true, true, authorities);
    }
}
