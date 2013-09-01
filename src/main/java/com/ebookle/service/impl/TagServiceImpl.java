package com.ebookle.service.impl;

import com.ebookle.dao.TagDAO;
import com.ebookle.entity.Tag;
import com.ebookle.service.TagService;
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
@org.springframework.stereotype.Service("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Override
    @Transactional
    public void saveOrUpdate (Tag tag) {
        tagDAO.saveOrUpdate(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findAll () {
        return tagDAO.findAll();
    }

    @Override
    @Transactional
    public boolean delete (int id) {
        return tagDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findById (int id) {
        return tagDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findTagByName (String name) {
        return tagDAO.findTagByName(name);
    }

    @Override
    @Transactional
    public List<Tag> findAllWithBooks () {
        return tagDAO.findAllWithBooks();
    }
}
