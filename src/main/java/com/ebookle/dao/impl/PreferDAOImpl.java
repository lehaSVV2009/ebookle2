package com.ebookle.dao.impl;

import com.ebookle.dao.CategoryDAO;
import com.ebookle.dao.PreferDAO;
import com.ebookle.dao.TagDAO;
import com.ebookle.entity.*;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class PreferDAOImpl extends AbstractDAOImpl<Prefer, Integer> implements PreferDAO {

    protected PreferDAOImpl () {
        super(Prefer.class);
    }

    @Override
    public Prefer findByBookAndMarkAuthor (Book book, User user) {
        return (Prefer) getSession().createCriteria(Prefer.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("book", book))
                .uniqueResult();
    }
}
