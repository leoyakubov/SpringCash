package io.leonid.springcash.web.user;


import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IUserService;
import io.leonid.springcash.web.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by leonid on 22.09.14.
 */
public class UserValidator extends GenericValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> paramClass) {
        return UserModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        super.validate(o, errors);

        UserModel model = (UserModel) o;

        //Is user being edited or added a new one?
        boolean isAdded = model.getPassword()!= null;

        if (isAdded) {
           //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
           //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "valid.passwordconf");

           if (!model.getPassword().equals(model.getConfirmedPassword())) {
                errors.rejectValue("confirmedPassword", "valid.passwordconfdiff");
           }

           //Check if the user's login is unique (on add new one)
           User user = userService.findByLogin(model.getLogin());
           if (user != null) {
                errors.rejectValue("login", "valid.userloginnotunique");
           }
        }

        if (model.getRole() == null) {
            errors.rejectValue("role", "valid.roleselect");
        }
    }
}
