package com.ebookle.controller;

/*
import com.ebookle.entity.Category;
import com.ebookle.entity.Chapter;
*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 1:55
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LogInController {

    @RequestMapping(value = "/welcome")
    public String welcomeUser (ModelMap modelMap, Principal principal) {
        return "redirect:/";
    }

    @RequestMapping(value = "/login_fail")
    public String loginFail (RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage", "Login was failed!");
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout (RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage", "You are log out");
        return "redirect:/";
    }

}
