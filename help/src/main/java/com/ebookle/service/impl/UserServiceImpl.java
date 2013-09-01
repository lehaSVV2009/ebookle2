package com.ebookle.service.impl;

import com.ebookle.dao.UserDAO;
import com.ebookle.entity.User;
import com.ebookle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:21
 * To change this template use File | Settings | File Templates.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void saveOrUpdate (User user) {
        userDAO.saveOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll () {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public boolean delete (int id) {
        return userDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById (int id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional
    public List<User> findAllWithDetails () {
        return userDAO.findAllWithDetails();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByRole (String role) {
        return userDAO.findAllByRole(role);
    }

    @Override
    @Transactional
    public User findByLogin (String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllNotActivatedByKey (String key) {
        return userDAO.findAllNotActivatedByKey(key);
    }


}
