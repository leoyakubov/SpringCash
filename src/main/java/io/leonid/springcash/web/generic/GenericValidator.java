package io.leonid.springcash.web.generic;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by leonid on 23.09.2014.
 */
public class GenericValidator implements org.springframework.validation.Validator, InitializingBean {
    private javax.validation.Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                String propertyPath = constraintViolation.getPropertyPath().toString();
                String message = constraintViolation.getMessage();
                errors.rejectValue(propertyPath, "", message);
            }
        }
}
