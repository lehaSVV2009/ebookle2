package com.ebookle.controller;

import com.ebookle.entity.Tag;
import com.ebookle.service.TagService;
import com.ebookle.util.UtilInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 01.09.13
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AutoCompleteController {

    @Autowired
    private TagService tagService;


    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET, headers = "Accept=*/*")
    public @ResponseBody
    List<String> autoCompleteTags (@RequestParam("term") String firstLetters) {
        List<String> tagNames = receiveTagNames(firstLetters);
        return tagNames;
    }

    private List<String> receiveTagNames (String firstLetters) {
        List<Tag> tags = tagService.findTagByFirstLetters(firstLetters, UtilInfo.MAX_AUTOCOMPLETE_TAGS_NUMBER);
        List<String> tagNames = new ArrayList<String>();
        for (Tag tag : tags) {
            tagNames.add(tag.getBookTag());
        }
        return tagNames;
    }

}
