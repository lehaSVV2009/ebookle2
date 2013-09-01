package com.ebookle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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


    private static DummyDB dummyDB = new DummyDB();

    @RequestMapping(value = "/get_country_list.html",
            method = RequestMethod.GET,
            headers="Accept=*/*")
    @ResponseBody
    protected List<String> getCountryList (@RequestParam("term") String query) {
        List<String> countryList = dummyDB.getCountryList(query);

        return countryList;
    }

    @RequestMapping(value = "/get_tech_list.html",
            method = RequestMethod.GET,
            headers="Accept=*/*")
    public @ResponseBody List<String> getTechList(@RequestParam("term") String query) {
        List<String> countryList = dummyDB.getTechList(query);

        return countryList;
    }

}
