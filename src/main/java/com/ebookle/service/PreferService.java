package com.ebookle.service;

import com.ebookle.entity.Book;
import com.ebookle.entity.Prefer;
import com.ebookle.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:31
 * To change this template use File | Settings | File Templates.
 */
public interface PreferService {

    void saveOrUpdate (Prefer chapter);
    List<Prefer> findAll ();
    boolean delete (int id);
    Prefer findById (int id);
    Prefer findByBookAndMarkAuthor (Book book, User user);
}
