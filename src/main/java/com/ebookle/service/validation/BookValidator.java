package com.ebookle.service.validation;

import com.ebookle.entity.Book;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
import com.ebookle.webmodel.BookCreationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 30.08.13
 * Time: 3:18
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BookValidator implements Validator {

    @Autowired
    private BookService bookService;

    @Override
    public boolean supports (Class<?> aClass) {
        return BookValidator.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate (Object target, Errors errors) {
        errors.rejectValue(
                "title",
                "title.empty",
                "Login is yet existed."
        );
    }

    public void validate (BookCreationForm form, Errors errors, User user) {

        String title = form.getTitle();
        validateText(title, errors, "title");
        List<Book> books = bookService.findAllByAuthor(user);
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                validate(form, errors);
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

}
