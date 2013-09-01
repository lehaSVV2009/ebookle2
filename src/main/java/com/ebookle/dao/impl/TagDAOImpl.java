package com.ebookle.dao.impl;

import com.ebookle.dao.CategoryDAO;
import com.ebookle.dao.TagDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.Category;
import com.ebookle.entity.Tag;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:11
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TagDAOImpl extends AbstractDAOImpl<Tag, Integer> implements TagDAO {

    protected TagDAOImpl () {
        super(Tag.class);
    }

    @Override
    public Tag findTagByName (String name) {
        return (Tag) getSession().createCriteria(Tag.class)
                .add(Restrictions.eq("bookTag", name))
                .uniqueResult();
    }

    @Override
    public List<Tag> findAllWithBooks () {
        return getSession().createCriteria(Tag.class).setFetchMode("books", FetchMode.EAGER)
                .list();
    }

    @Override
    public List<Tag> findByPopularity (int maxResult) {
        return getSession().createCriteria(Tag.class)
                .addOrder(Order.desc("counter"))
                .setMaxResults(maxResult)
                .list();
    }

    @Override
    public List<Tag> findTagByFirstLetters (String firstLetters, int maxResult) {
        return getSession().createCriteria(Tag.class)
                .setMaxResults(maxResult)
                .add(Restrictions.like("bookTag", firstLetters + "%"))
                .list();
    }
}
