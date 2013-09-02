package com.ebookle.controller;

import com.ebookle.entity.Book;
import com.ebookle.entity.Category;
import com.ebookle.entity.Tag;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
import com.ebookle.service.CategoryService;
import com.ebookle.service.TagService;
import com.ebookle.service.UserService;
import com.ebookle.util.UtilInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 6:37
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/home/{searchType}")
    public String showHomePage (ModelMap modelMap,
                                Principal principal,
                                RedirectAttributes redirectAttributes,
                                @PathVariable("searchType") String searchType) {
        makeCloud(modelMap);
        if (! makeBooksList(modelMap, searchType)) {
            return showFlashMessage("Bad database", redirectAttributes);
        }
        if (principal == null) {
            adjustPersonRole(UtilInfo.GUEST_PERSON, modelMap);
            return "home";
        }
        adjustUserOnHomePage(principal, modelMap);
        return "home";
    }

    public void makeCloud (ModelMap modelMap) {

        List<Tag> tags = tagService.findByPopularity(UtilInfo.CLOUD_REFERENCES_NUMBER);
        modelMap.addAttribute("tags", tags);
    }

    private boolean makeBooksList (ModelMap modelMap, String searchType) {

        List<Category> categories = categoryService.findAll();
        List<Book> books = getBooksBySearchType(searchType, categories);
        if (books == null) {
            return false;
        }
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("categories", categories);
        return true;
    }

    private void adjustPersonRole (String role, ModelMap modelMap) {

        modelMap.addAttribute("person", role);
    }

    private void adjustUserOnHomePage (Principal principal, ModelMap modelMap) {

        String login = principal.getName();
        User user = userService.findByLogin(login);
        if (user.getRole().equals(UtilInfo.USER_ROLE_TEXT)) {
            adjustPersonRole(UtilInfo.USER_PERSON, modelMap);
        } else {
            adjustPersonRole(UtilInfo.ADMIN_PERSON, modelMap);
        }
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("userBooks", user.getBooks());
    }

    private List<Book> getBooksBySearchType (String searchType, List<Category> categories) {

        List<Book> books = new ArrayList<Book>();
        if ("mostPopular".equals(searchType)) {
            books = bookService.findMostPopularWithAuthors();
        } else if ("recent".equals(searchType)) {
            books = bookService.findRecentWithAuthors();
        } else if ("random".equals(searchType)) {
            books = bookService.findAll();
        } else {
            for (Category category : categories) {
                if (category.getName().equals(searchType)) {
                    books = bookService.findByCategoryWithAuthors(category);
                    break;
                }
            }
        }
        return books;
    }

    @RequestMapping(value = "/")
    public String goHome () {
        return "redirect:/home/mostPopular";
    }

    @RequestMapping("/home")
    public String goHomeFromHomeUrl () {
        return "redirect:/";
    }

    private String showFlashMessage (String flashMessage, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage", flashMessage);
        return "home";
    }


}
