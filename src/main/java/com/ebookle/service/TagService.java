package com.ebookle.service;

import com.ebookle.entity.Tag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:36
 * To change this template use File | Settings | File Templates.
 */
public interface TagService {

    void saveOrUpdate (Tag tag);
    List<Tag> findAll ();
    boolean delete (int id);
    Tag findById (int id);
    Tag findTagByName(String name);
    List<Tag> findAllWithBooks ();
    List<Tag> findByPopularity(int maxNumber);
    public List<Tag> findTagByFirstLetters (String firstLetters, int maxResult);
}
