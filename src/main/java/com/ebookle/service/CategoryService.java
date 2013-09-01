package com.ebookle.service;

import com.ebookle.entity.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:30
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryService {

    void saveOrUpdate (Category category);
    List<Category> findAll ();
    boolean delete (int id);
    Category findById (int id);
}
