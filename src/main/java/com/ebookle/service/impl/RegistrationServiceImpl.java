package com.ebookle.service.impl;

import com.ebookle.entity.User;
import com.ebookle.service.RegistrationService;
import com.ebookle.util.RandomKeyCreator;
import com.ebookle.util.UtilStrings;
import com.ebookle.webmodel.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 25.08.13
 * Time: 5:51
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserServiceImpl userService;


    protected void sendKeyToMail (String to, String topic, String message) {
        preConfiguredMessage.setTo(to);
        preConfiguredMessage.setSubject(topic);
        preConfiguredMessage.setText(message);
        mailSender.send(preConfiguredMessage);
    }

    @Override
    public void register (User user) {
        String userKey = RandomKeyCreator.createRandomKey();
        user.setRegisteredKey(userKey);
        userService.saveOrUpdate(user);
        sendKeyToMail(
                user.getEmail(),
                "Registration site ebookle.com",
                "http://localhost:8080/registration_success?key=" + userKey
        );
    }

    @Override
    public void register (RegistrationForm form) {
        String userKey = RandomKeyCreator.createRandomKey();
        User user = new User(
                form.getLogin(),
                form.getPassword(),
                form.getEmail(),
                form.getName(),
                form.getSurname(),
                userKey,
                "ROLE_USER",
                false
        );
        userService.saveOrUpdate(user);
        sendKeyToMail(
                user.getEmail(),
                "Registration site ebookle.com",
                "http://localhost:8080/registration_success?key=" + userKey
        );
    }

    public boolean activateUser(String key) {
        List<User> users = userService.findAllNotActivatedByKey(key);
        if (users.size() != 1) {
            return false;
        }
        User user = users.get(0);
        user.setActivated(true);
        userService.saveOrUpdate(user);
        return true;
    }
}
