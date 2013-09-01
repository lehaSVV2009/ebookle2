package com.ebookle.dao.impl;

import com.ebookle.dao.ChapterDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.Chapter;
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
public class ChapterDAOImpl extends AbstractDAOImpl<Chapter, Integer> implements ChapterDAO {

    protected ChapterDAOImpl () {
        super(Chapter.class);
    }

    @Override
    public Chapter findByBookAndChapterNumber (Book book, Integer chapterNumber) {
        return (Chapter) getSession().createCriteria(Chapter.class)
                .add(Restrictions.eq("book", book))
                .add(Restrictions.eq("chapterNumber", chapterNumber))
                .uniqueResult();
    }

    @Override
    public List<Chapter> searchAll(String searchString) {
        FullTextSession fullTextSession = Search.getFullTextSession(getSession());
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Chapter.class ).get();
        org.apache.lucene.search.Query query =
                qb.keyword().onFields("text","title").matching(searchString).createQuery();
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Book.class);
        return hibQuery.list();
    }


}
