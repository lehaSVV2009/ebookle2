package com.ebookle.service;

import com.ebookle.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:37
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    public void saveOrUpdate (User user);
    public List<User> findAll ();
    public boolean delete (int id);
    public User findById (int id);
    public List<User> findAllWithDetails ();
    public List<User> findAllByRole (String role);
    public User findByLogin (String login);
    public List<User> findAllNotActivatedByKey (String key);
}
