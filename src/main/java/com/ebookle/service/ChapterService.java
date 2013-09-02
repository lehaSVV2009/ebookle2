package com.ebookle.service;

import com.ebookle.entity.Book;
import com.ebookle.entity.Chapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:31
 * To change this template use File | Settings | File Templates.
 */
public interface ChapterService {

    void saveOrUpdate (Chapter chapter);
    List<Chapter> findAll ();
    boolean delete (int id);
    Chapter findById (int id);
    Chapter findByBookAndChapterNumber(Book book, Integer chapterNumber);
    List<Chapter> findAllByBook (Book book);
    List<Chapter> searchTitleAndDescription (String searchString);
}
