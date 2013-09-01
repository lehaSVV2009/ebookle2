package com.ebookle.controller;

import com.ebookle.entity.User;
import com.ebookle.service.impl.RegistrationServiceImpl;
import com.ebookle.service.validation.RegistrationValidator;
import com.ebookle.util.UtilStrings;
import com.ebookle.webmodel.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 6:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private RegistrationValidator validator;

    @RequestMapping("/register")
    public String register (RedirectAttributes redirectAttributes,
                            @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                            BindingResult errors) {

        validator.validate(registrationForm, errors);
        if (errors.hasErrors()) {
            redirectAttributes.addFlashAttribute("badInput", UtilStrings.BAD_INPUT);
            return "redirect:/registration";
        }
        registrationService.register(registrationForm);
        redirectAttributes.addFlashAttribute("goodMessage", UtilStrings.SEND_DATA_SUCCESS);
        return "redirect:/";
    }

    @RequestMapping(value = "/registration_success", method = RequestMethod.GET)
    public String registrationSuccess (ModelMap modelMap, @RequestParam("key") String key) {

        return registrationService.activateUser(key)
                ? "registration_success" : "registration_failed";
    }

    @RequestMapping(value = "/registration")
    public String goToRegistration (ModelMap modelMap) {
        return "registration";
    }
}
