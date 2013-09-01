package com.ebookle.service.impl;

import com.ebookle.dao.PreferDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.Prefer;
import com.ebookle.entity.User;
import com.ebookle.service.PreferService;
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
@org.springframework.stereotype.Service("preferService")
public class PreferServiceImpl implements PreferService {

    @Autowired
    private PreferDAO preferDAO;

    @Override
    @Transactional
    public void saveOrUpdate (Prefer chapter) {
        preferDAO.saveOrUpdate(chapter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prefer> findAll () {
        return preferDAO.findAll();
    }

    @Override
    @Transactional
    public boolean delete (int id) {
        return preferDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Prefer findById (int id) {
        return preferDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Prefer findByBookAndMarkAuthor (Book book, User user) {
        return preferDAO.findByBookAndMarkAuthor(book, user);
    }

}
