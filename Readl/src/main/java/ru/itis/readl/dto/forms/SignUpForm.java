package ru.itis.readl.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.readl.validation.annotations.AccountAlreadyExist;
import ru.itis.readl.validation.annotations.FieldsMatch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldsMatch(fields = {"password", "repeatPassword"}, message = "Passwords are different")
@AccountAlreadyExist
public class SignUpForm {

    @NotBlank(message = "Enter the nickname")
    @Size(min = 3, max = 10, message = "Nickname should have length more than {min} and less than {max}")
    @Pattern(regexp="[a-zA-Zа-яА-Я0-9_.\\-]+", message = "Nickname is incorrect")
    private String nickname;

    @NotBlank(message = "Enter the email")
    @Pattern(regexp="[A-Za-z0-9_+\\-.]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}", message = "Email is incorrect")
    private String email;

    @NotBlank(message = "Enter the password")
    @Size(min = 6, max = 10, message = "Password should have length more than {min} and less than {max}")
    private String password;

    @NotBlank(message = "Enter the repeat password")
    @Size(min = 6, max = 10, message = "Password should have length more than {min} and less than {max}")
    private String repeatPassword;

}
