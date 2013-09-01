package com.ebookle.service;

import com.ebookle.entity.User;
import com.ebookle.webmodel.RegistrationForm;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:33
 * To change this template use File | Settings | File Templates.
 */
public interface RegistrationService {

    void register (User user);
    void register (RegistrationForm form);
    boolean activateUser (String key);
}
