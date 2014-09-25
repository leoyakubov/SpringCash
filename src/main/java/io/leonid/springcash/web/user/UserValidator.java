package io.leonid.springcash.web.user;


import io.leonid.springcash.web.GenericValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by leonid on 22.09.14.
 */
public class UserValidator extends GenericValidator implements Validator {
    @Override
    public boolean supports(Class<?> paramClass) {
        return UserModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        super.validate(o, errors);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "valid.passwordconf");

        UserModel model = (UserModel) o;
        if (!model.getPassword().equals(model.getConfirmedPassword())) {
            errors.rejectValue("confirmedPassword", "valid.passwordconfdiff");
        }

        if (model.getRole() == null) {
            errors.rejectValue("role", "valid.roleselect");
        }
    }
}
