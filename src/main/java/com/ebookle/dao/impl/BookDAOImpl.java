package com.ebookle.dao.impl;

import com.ebookle.dao.BookDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.Category;
import com.ebookle.entity.User;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
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
public class BookDAOImpl extends AbstractDAOImpl<Book, Integer> implements BookDAO {

    public BookDAOImpl () {
        super(Book.class);
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Book> findAllWithAuthors () {
        return getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .list();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Book findByIdWithAuthor (int id) {
        return (Book) getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Book findByTitleAndUserIdWithChapters (String title, User user) {
        return (Book) getSession().createCriteria(Book.class).setFetchMode("chapters", FetchMode.EAGER)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("title", title))
                .uniqueResult();
    }

    @Override
    public Book findByTitleAndUserId (String title, User user) {
        return (Book) getSession().createCriteria(Book.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("title", title))
                .uniqueResult();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Book findByTitleAndUserIdWithTags (String title, User user) {
        return (Book) getSession().createCriteria(Book.class).setFetchMode("tags", FetchMode.EAGER)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("title", title))
                .uniqueResult();
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Book> findMostPopularWithAuthors () {
        return getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .addOrder(Order.desc("rating"))
                .list();
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Book> findRecentWithAuthors () {
        return getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .addOrder(Order.asc("id"))
                .list();
    }

    @Override
    public boolean deleteBookFromBookTag (Integer id) {
        //TODO: delete boolId from table booktag
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Book> findByCategoryWithAuthors (Category category) {
        return getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .add(Restrictions.eq("category", category))
                .addOrder(Order.asc("id"))
                .list();
    }

    @Override
    public List<Book> findByCategory (Category category) {
        return getSession().createCriteria(Book.class)
                .add(Restrictions.eq("category", category))
                .list();
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Book> findAllByAuthor (User user) {
        return getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .add(Restrictions.eq("user", user))
                .addOrder(Order.asc("id"))
                .list();
    }

    @Override
    public List<Book> searchCaptions(String searchString) {
        FullTextSession fullTextSession = Search.getFullTextSession(getSession());
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Book.class ).get();
        org.apache.lucene.search.Query query =
                qb.keyword().onFields("description","title","tags.bookTag","category.name").matching(searchString).createQuery();
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Book.class);
        return hibQuery.list();
    }

    @Override
    public List<Book> searchTag(String searchString) {
        FullTextSession fullTextSession = Search.getFullTextSession(getSession());
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Book.class ).get();
        org.apache.lucene.search.Query query =
                qb.keyword().onFields("tags.bookTag").matching(searchString).createQuery();
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Book.class);
        return hibQuery.list();
    }

    @Override
    public List<Book> searchCategory(String searchString) {
        FullTextSession fullTextSession = Search.getFullTextSession(getSession());
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Book.class ).get();
        org.apache.lucene.search.Query query =
                qb.keyword().onFields("category").matching(searchString).createQuery();
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Book.class);
        return hibQuery.list();
    }

    @Override
    public List<Book> searchTitleAndDescription(String searchString) {
        FullTextSession fullTextSession = Search.getFullTextSession(getSession());
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Book.class ).get();
        org.apache.lucene.search.Query query =
                qb.keyword().onFields("description","title").matching(searchString).createQuery();
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Book.class);
        return hibQuery.list();
    }

}
