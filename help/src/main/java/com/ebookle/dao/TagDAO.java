package com.ebookle.dao;


import com.ebookle.entity.Tag;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public interface TagDAO extends AbstractDAO<Tag, Integer> {

    public Tag findTagByName (String name);
    public List<Tag> findAllWithBooks ();
    public List<Tag> searchByName(String name);
}
