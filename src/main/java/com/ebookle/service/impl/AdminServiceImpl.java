package com.ebookle.service.impl;

import com.ebookle.entity.User;
import com.ebookle.service.AdminService;
import com.ebookle.service.UserService;
import com.ebookle.util.UtilInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 25.08.13
 * Time: 5:51
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    @Transactional
    public boolean removeUser (Integer id) {
        return userService.delete(id);
    }

    @Override
    @Transactional
    public List<User> getUsersHasRoleUser () {
        return userService.findAllByRole(UtilInfo.USER_ROLE_TEXT);
    }

    public UserService getUserService () {
        return userService;
    }

    public void setUserService (UserServiceImpl userService) {
        this.userService = userService;
    }

}
