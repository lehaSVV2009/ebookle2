package com.ebookle.service;

import com.ebookle.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:24
 * To change this template use File | Settings | File Templates.
 */
public interface AdminService {

    boolean removeUser (Integer id);
    List<User> getUsersHasRoleUser ();
}
