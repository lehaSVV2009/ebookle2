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

    @RequestMapping(value = "/test")
    public String test1 () {
        return "test";
    }

    @RequestMapping(value = "/home/{searchType}")
    public String showMostPopularBooks (ModelMap modelMap,
                                        Principal principal,
                                        RedirectAttributes redirectAttributes,
                                        @PathVariable("searchType") String searchType) {
        try {

            makeCloud(modelMap);
            List<Book> books = new ArrayList<Book>();
            List<Category> categories = categoryService.findAll();
            if ("mostPopular".equals(searchType)) {
                books = bookService.findMostPopularWithAuthors();
            } else if ("recent".equals(searchType)) {
                books = bookService.findRecentWithAuthors();
            } else {
                for (Category category : categories) {
                    if (category.getName().equals(searchType)) {
                        books = bookService.findByCategoryWithAuthors(category);
                        break;
                    }
                }
            }
            if (books == null) {
                return showFlashMessage("Bad database", redirectAttributes);
            }
            modelMap.addAttribute("books", books);
            modelMap.addAttribute("categories", categories);
            if (principal == null) {
                modelMap.addAttribute("person", UtilInfo.GUEST_PERSON);
                return "home";
            }
            String login = principal.getName();
            User user = userService.findByLogin(login);
            if (user == null) {
                return showFlashMessage("Bad database", redirectAttributes);
            }
            if (user.getRole().equals(UtilInfo.USER_ROLE_TEXT)){
                modelMap.addAttribute("person", UtilInfo.USER_PERSON);
            } else {
                modelMap.addAttribute("person", UtilInfo.ADMIN_PERSON);
            }
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("userBooks", user.getBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home";
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

    public void makeCloud(ModelMap modelMap) {

        List<Tag> tags = tagService.findByPopularity(UtilInfo.CLOUD_REFERENCES_NUMBER);
        modelMap.addAttribute("tags", tags);
    }


}
