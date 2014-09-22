package io.leonid.springcash.web.user;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by leonid on 22.09.14.
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> paramClass) {
        return UserModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "valid.passwordConf");

        UserModel model = (UserModel) o;

        if (!model.getPassword().equals(model.getConfirmedPassword())) {
            errors.rejectValue("confirmedPassword", "valid.passwordConfDiff");
        }
    }
}
