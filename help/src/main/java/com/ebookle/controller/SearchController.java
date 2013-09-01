package com.ebookle.controller;

import com.ebookle.entity.Book;
import com.ebookle.service.BookService;
import com.ebookle.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 5:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;

//    @Secured("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/searchAll", method = RequestMethod.POST)
    public String test (@RequestParam("searchString") String searchString,ModelMap modelMap){
        List<Book> books = bookService.searchAll(searchString);
        modelMap.addAttribute("books", books);
        return "search_result";
    }
//    @RequestMapping(value = "/searchAll", method = RequestMethod.POST)
//    public String searchChapter (@RequestParam("searchString") String searchString,ModelMap modelMap){
//        List<Chapter> chapters = chapterService.searchAll(searchString);
//        modelMap.addAttribute("chapters", chapters);
//        return "search_result";
//    }
}
