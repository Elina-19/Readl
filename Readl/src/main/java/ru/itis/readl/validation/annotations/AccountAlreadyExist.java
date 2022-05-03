package ru.itis.readl.validation.annotations;

import ru.itis.readl.validation.validators.AccountAlreadyExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountAlreadyExistValidator.class)
public @interface AccountAlreadyExist {

    String[] fields() default {};

    String message() default "Account with that email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
