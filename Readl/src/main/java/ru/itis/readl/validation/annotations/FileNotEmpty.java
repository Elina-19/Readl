package ru.itis.readl.validation.annotations;

import ru.itis.readl.validation.validators.AccountAlreadyExistValidator;
import ru.itis.readl.validation.validators.FileNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileNotEmptyValidator.class)
public @interface FileNotEmpty {

    String[] fields() default {};

    String message() default "Upload the file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
