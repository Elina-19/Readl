package ru.itis.readl.controllers.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.readl.exceptions.ReadlServiceException;
import ru.itis.readl.validation.http.ValidationErrorDto;
import ru.itis.readl.validation.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public String onAllExceptions(Exception exception, Model model) {

        log.error("Internal server error exception: ", exception.getMessage());

        model.addAttribute("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
        return "code_pages/errorPage";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleValidExceptions(MethodArgumentNotValidException exception){

        log.debug("ValidationException has occurred");

        List<ValidationErrorDto> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {

            errors.add(ValidationErrorDto.builder()
                    .message(error.getDefaultMessage())
                    .build());
        });

        return new ResponseEntity<>(ValidationExceptionResponse.builder()
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String onAccessDeniedException(AccessDeniedException exception, Model model) {

        log.debug("Exception has occurred: " + exception.getMessage());

        model.addAttribute("statusCode", HttpStatus.FORBIDDEN);

        return "code_pages/errorPage";
    }

    @ExceptionHandler(ReadlServiceException.class)
    public String onReadlServiceException(ReadlServiceException readlServiceException, Model model) {

        log.debug("Exception has occurred: " + readlServiceException.getMessage());

        model.addAttribute("statusCode", readlServiceException.getHttpStatus());

        return "code_pages/errorPage";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String onAuthenticationExceptions(AuthenticationException authenticationException, Model model) {

        log.debug("Exception has occurred: " + authenticationException.getMessage());

        model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED);

        return "code_pages/errorPage";
    }
}
