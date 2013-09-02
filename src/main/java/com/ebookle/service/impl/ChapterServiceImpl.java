package com.ebookle.service.impl;

import com.ebookle.dao.ChapterDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.Chapter;
import com.ebookle.service.ChapterService;
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
@org.springframework.stereotype.Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    @Transactional
    public void saveOrUpdate (Chapter chapter) {
        chapterDAO.saveOrUpdate(chapter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chapter> findAll () {
        return chapterDAO.findAll();
    }

    @Override
    @Transactional
    public boolean delete (int id) {
        return chapterDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Chapter findById (int id) {
        return chapterDAO.findById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Chapter findByBookAndChapterNumber (Book book, Integer chapterNumber) {
        return chapterDAO.findByBookAndChapterNumber(book, chapterNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chapter> findAllByBook (Book book) {
        return chapterDAO.findAllByBook(book);
    }

    @Override
    @Transactional
    public List<Chapter> searchTitleAndDescription(String searchString) {
        return chapterDAO.searchTitleAndDescription(searchString);
    }

}
