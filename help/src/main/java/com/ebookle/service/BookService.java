package com.ebookle.service;

import com.ebookle.entity.Book;
import com.ebookle.entity.Category;
import com.ebookle.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:29
 * To change this template use File | Settings | File Templates.
 */
public interface BookService {

    void saveOrUpdate (Book book);
    List<Book> findAll ();
    boolean delete (Integer id);
    Book findById (int id);
    Book findByIdWithAuthor (int id);
    List<Book> findAllWithAuthors ();
    Book findByTitleAndUserIdWithChapters (String title, User user);
    Book findByTitleAndUserId (String title, User user);
    Book findByTitleAndUserIdWithTags (String title, User user);
    List<Book> findMostPopularWithAuthors ();
    List<Book> findRecentWithAuthors ();
    List<Book> findByCategoryWithAuthors (Category category);
    List<Book> findByCategory (Category category);
    List<Book> findAllByAuthor (User user);
    List<Book> searchAll (String searchString);
}
