package org.insta.wrapper.hibernate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

public record Validate(Validator validator) {

    private static final Validate validate = new Validate(
            Validation.byProvider(HibernateValidator.class)
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator());

    public static Validate getInstance() {
        return validate;
    }

    public <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?> groups) {
        return validator.validate(object, groups);
    }
}