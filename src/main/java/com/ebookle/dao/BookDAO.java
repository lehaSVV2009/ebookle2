package com.ebookle.dao;

import com.ebookle.entity.Book;
import com.ebookle.entity.Category;
import com.ebookle.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public interface BookDAO extends AbstractDAO<Book, Integer> {

    public List<Book> findAllWithAuthors ();
    public Book findByIdWithAuthor (int id);
    public Book findByTitleAndUserIdWithChapters (String title, User user);
    public Book findByTitleAndUserId (String title, User user);
    public Book findByTitleAndUserIdWithTags (String title, User user);
    public List<Book> findMostPopularWithAuthors ();
    public List<Book> findRecentWithAuthors ();
    public boolean deleteBookFromBookTag (Integer id);
    public List<Book> findByCategoryWithAuthors (Category category);
    public List<Book> findByCategory (Category category);
    public List<Book> findAllByAuthor (User user);
}