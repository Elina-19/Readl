package ru.itis.readl.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInForm {

    @NotBlank(message = "Enter the email")
    private String email;

    @NotBlank(message = "Enter the password")
    private String password;
}
