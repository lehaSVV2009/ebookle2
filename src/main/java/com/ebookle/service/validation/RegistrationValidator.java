package com.ebookle.service.validation;


import com.ebookle.entity.Book;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
import com.ebookle.service.UserService;
import com.ebookle.webmodel.RegistrationForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RegistrationValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports (Class<?> aClass) {
        return RegistrationForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate (Object target, Errors errors) {

        RegistrationForm form = (RegistrationForm) target;
        validateLogin(form.getLogin(), errors);
        validateTextWithoutSpaces(form.getPassword(), errors, "password");
        validateText(form.getName(), errors, "name");
        validateText(form.getSurname(), errors, "surname");
        validateEmail(form.getEmail(), errors);
    }

    private void validateLogin (String login, Errors errors) {

        validateTextWithoutSpaces(login, errors, "login");
        List<User> users = userService.findAll();
        for (User user : users) {
            if (login.equalsIgnoreCase(user.getLogin())) {
                errors.rejectValue(
                        "login",
                        "login.empty",
                        "Login is yet existed."
                );
                break;
            }
        }
    }

    private void validateText (String text, Errors errors, String type) {

        if (text == null || "".equals(text.trim())) {
            errors.rejectValue(
                    type,
                    type + ".empty",
                    type + " is wrong."
            );
        }
    }

    private void validateTextWithoutSpaces (String text, Errors errors, String type) {
        validateText(text, errors, type);
        if (text != null && text.contains(" ")) {
            errors.rejectValue(
                    type,
                    type + ".emp",
                    type + " has spaces."
            );
        }
    }

    private void validateEmail (String email, Errors errors) {
        if (! EmailValidator.getInstance().isValid(email)) {
            errors.rejectValue(
                    "email",
                    "email.notValid",
                    "Email is wrong."
            );
        }
    }
}
