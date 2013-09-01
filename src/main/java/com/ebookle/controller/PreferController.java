package com.ebookle.controller;

import com.ebookle.entity.Book;
import com.ebookle.entity.Prefer;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
import com.ebookle.service.PreferService;
import com.ebookle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 31.08.13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PreferController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private PreferService preferService;

    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping("/{userLogin}/editBook/{bookTitle}/{chapterNumber}/show/{mark}")
    public String markBook (@PathVariable("userLogin") String userLogin,
                            @PathVariable("bookTitle") String bookTitle,
                            @PathVariable("chapterNumber") Integer chapterNumber,
                            @PathVariable("mark") int mark,
                            ModelMap modelMap,
                            Principal principal) {
        Book book = findBookByTitleAndAuthorLogin(userLogin, bookTitle);
        if (book == null || (mark != 1 && mark != -1)) {
            return sendErrorMessage(modelMap, "Page not found");
        }
        User markAuthor = userService.findByLogin(principal.getName());
        Prefer userPrefer = adjustUserPrefer(book, markAuthor, mark);
        preferService.saveOrUpdate(userPrefer);
        bookService.saveOrUpdate(book);
        return "redirect:/" + userLogin + "/editBook/" + bookTitle + "/" +  chapterNumber + "/show";
    }

    private Prefer adjustUserPrefer (Book book, User markAuthor, int mark) {
        Prefer userPrefer = preferService.findByBookAndMarkAuthor(book, markAuthor);
        if (userPrefer == null) {
            userPrefer = createNewPrefer(mark, book, markAuthor);
            book.setRating(book.getRating() + mark);
        } else {
            int oldMark = userPrefer.getMark();
            if (oldMark == mark) {
                userPrefer.setMark((- mark));
                book.setRating(book.getRating() - mark);
            } else {
                userPrefer.setMark(mark);
                book.setRating(book.getRating() + mark);
            }
        }
        return userPrefer;
    }

    private Prefer createNewPrefer(int mark, Book book, User markAuthor) {
        Prefer userPrefer = new Prefer();
        userPrefer.setMark(mark);
        userPrefer.setBook(book);
        userPrefer.setUser(markAuthor);
        return userPrefer;
    }

    private Book findBookByTitleAndAuthorLogin (String userLogin, String bookTitle) {
        User author = userService.findByLogin(userLogin);
        if(author == null) {
            return null;
        }
        return bookService.findByTitleAndUserId(bookTitle, author);
    }

    private String sendErrorMessage(ModelMap modelMap, String error) {
        modelMap.addAttribute("flashMessage", error);
        return "redirect:/";
    }

}
