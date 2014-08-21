package io.leonid.springcash.web;

import io.leonid.springcash.model.User;
import io.leonid.springcash.service.RoleService;
import io.leonid.springcash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by leonid on 14.08.14.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/users.htm")
    public String listUsers(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.findAll());
        map.put("roleList", roleService.findAll());

        return "users";
    }

    @RequestMapping(value = "/users.htm", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("user")
                             User user, BindingResult result) {
        userService.insertOrUpdate(user);

        return "redirect:/users.htm";
    }

    @RequestMapping("/deleteUser/{userId}")
    public String deleteContact(@PathVariable("userId")
                                Integer userId) {
        User user = userService.findByID(userId);
        userService.delete(user);

        return "redirect:/users";
    }
}
