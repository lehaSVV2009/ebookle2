package com.ebookle.dao;


import com.ebookle.entity.Book;
import com.ebookle.entity.Prefer;
import com.ebookle.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public interface PreferDAO extends AbstractDAO<Prefer, Integer> {

    public Prefer findByBookAndMarkAuthor (Book book, User user);
}
