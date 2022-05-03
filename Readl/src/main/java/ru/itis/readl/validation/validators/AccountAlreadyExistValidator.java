package ru.itis.readl.validation.validators;

import lombok.RequiredArgsConstructor;
import ru.itis.readl.dto.forms.SignUpForm;
import ru.itis.readl.services.AccountsService;
import ru.itis.readl.validation.annotations.AccountAlreadyExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class AccountAlreadyExistValidator implements ConstraintValidator<AccountAlreadyExist, SignUpForm> {

    private final AccountsService accountsService;

    @Override
    public boolean isValid(SignUpForm signUpForm, ConstraintValidatorContext constraintValidatorContext) {
        return !accountsService.exists(signUpForm.getEmail());
    }
}
