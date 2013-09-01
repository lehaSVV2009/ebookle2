package com.ebookle.service.impl;

import com.ebookle.dao.BookDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.Category;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
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
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public void saveOrUpdate (Book book) {
        bookDAO.saveOrUpdate(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll () {
        return bookDAO.findAll();
    }

    @Override
    @Transactional
    public boolean delete (Integer id) {

        //  TODO: delete all connections with tags
        return bookDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById (int id) {
        return bookDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByIdWithAuthor (int id) {
        return bookDAO.findByIdWithAuthor(id);
    }

    @Override
    @Transactional
    public List<Book> findAllWithAuthors () {
        return bookDAO.findAllWithAuthors();
    }


    @Override
    @Transactional(readOnly = true)
    public Book findByTitleAndUserIdWithChapters (String title, User user) {
        return bookDAO.findByTitleAndUserIdWithChapters(title, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByTitleAndUserId (String title, User user) {
        return bookDAO.findByTitleAndUserId(title, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByTitleAndUserIdWithTags (String title, User user) {
        return bookDAO.findByTitleAndUserIdWithTags(title, user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findMostPopularWithAuthors () {
        return bookDAO.findMostPopularWithAuthors();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findRecentWithAuthors () {
        return bookDAO.findRecentWithAuthors();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByCategoryWithAuthors (Category category) {
        return bookDAO.findByCategoryWithAuthors(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByCategory (Category category) {
        return bookDAO.findByCategory(category);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllByAuthor (User user) {
        return bookDAO.findAllByAuthor(user);
    }

    @Override
    @Transactional
    public List<Book> searchAll(String searchString) {
        return bookDAO.searchAll(searchString);
    }
}
