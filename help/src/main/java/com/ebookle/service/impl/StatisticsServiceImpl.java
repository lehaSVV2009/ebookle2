package com.ebookle.service.impl;


import com.ebookle.entity.Book;
import com.ebookle.entity.Category;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
import com.ebookle.service.CategoryService;
import com.ebookle.service.StatisticsService;
import com.ebookle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 30.08.13
 * Time: 7:07
 * To change this template use File | Settings | File Templates.
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Override
    public Map<Category, Integer> getBookCategoryStatistics () {
        Map<Category, Integer> map = new HashMap<Category, Integer>();
        List<Category> categories = categoryService.findAll();
        for (Category category : categories) {
            map.put(category, bookService.findByCategory(category).size());
        }
        return map;
    }
}
