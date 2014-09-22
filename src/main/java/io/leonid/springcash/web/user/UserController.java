package io.leonid.springcash.web.user;

import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IRoleService;
import io.leonid.springcash.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by leonid on 14.08.14.
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping("/user/list.htm")
    public String listUsers(Map<String, Object> map) {
        logger.info("UserController.listUsers called");
        //map.put("user", new User());
        map.put("userList", userService.findAll());
        map.put("roleList", roleService.findAll());

        return "user/list";
    }

    @RequestMapping(value = "/user/add.htm", method = RequestMethod.GET)
    public String prepareAddUserForm(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.put("roleList", roleService.findAll());

        return "user/add";
    }

    @RequestMapping(value = "/user/add.htm", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")
                             User user, BindingResult result) {
        userService.insertOrUpdate(user);

        return "redirect:/user/list.htm";
    }

    @RequestMapping(value = "/user/edit.htm", method = RequestMethod.GET)
    public String prepareEditUserForm(HttpServletRequest request, ModelMap modelMap) {
        String userId = request.getParameter("userId");
        User user = userService.findByID(Integer.parseInt(userId));

        UserModel userModel = new UserModel(user);

        modelMap.addAttribute("userModel", userModel);
        modelMap.put("roleList", roleService.findAll());

        return "user/edit";
    }

    @RequestMapping(value = "/user/edit.htm", method = RequestMethod.POST)
    public String editUser(@Validated @ModelAttribute("userModel")
                                  UserModel userModel, BindingResult result) {
        if(result.hasErrors()) {
            return "user/edit";
        }

        userModel.setRole(roleService.findByID(userModel.getRole().getId()));
        User user = userModel.createUserFromModel();
        userService.insertOrUpdate(user);

        return "redirect:/user/list.htm";
    }

    @RequestMapping("/user/delete.htm")
    public String deleteUser(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        User user = userService.findByID(Integer.parseInt(userId));
        userService.delete(user);

        return "redirect:/user/list.htm";
    }
}
