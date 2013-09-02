package com.ebookle.controller;

import com.ebookle.entity.Book;
import com.ebookle.entity.Chapter;
import com.ebookle.service.BookService;
import com.ebookle.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    public String searchCaptions (String searchString,Integer options,ModelMap modelMap){
        List<Book> books = bookService.searchCaptions(searchString);
        modelMap.addAttribute("books", books);
        return "search_result";
    }
    public String searchTags (String searchString,Integer options,ModelMap modelMap){
        List<Book> books = bookService.searchTag(searchString);
        modelMap.addAttribute("books", books);
        return "search_result";
    }

    public String searchContent (String searchString,Integer options,ModelMap modelMap){
        List<Chapter> chapters = chapterService.searchTitleAndDescription(searchString);
        List<Book> books = new ArrayList<Book>();
        for (Chapter chapter : chapters){
            if (!books.contains(chapter.getBook())){
                books.add(chapter.getBook());
            }
        }
        modelMap.addAttribute("books", books);
        return "search_result";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchOptions (@RequestParam("searchString") String searchString,
                                 @RequestParam("options") Integer options,
                                 ModelMap modelMap){
        if(options==1){
            return searchTags(searchString,options,modelMap);
        }else if(options==2){
            return searchCaptions(searchString,options,modelMap);
        }else if(options==3){
            return searchContent(searchString,options,modelMap);
        }
        return null;
    }
}
