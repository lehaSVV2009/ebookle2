package com.ebookle.controller;

import com.ebookle.entity.Category;
import com.ebookle.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 6:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/statistics/authorCounts", method = RequestMethod.GET)
    public String showBooksCountStatistics (ModelMap modelMap) {

        Map<Category, Integer> statistics = statisticsService.getBookCategoryStatistics();
        modelMap.addAttribute("bookCategoryStatistics", statistics);
        return "statistics";
    }

}
