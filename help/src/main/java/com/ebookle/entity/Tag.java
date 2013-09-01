package com.ebookle.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
@Table(name = "Tag")
public class Tag implements Entity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bookTag", unique = true)
    private String bookTag;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Book> books = new ArrayList<Book>();

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getBookTag () {
        return bookTag;
    }

    public void setBookTag (String bookTag) {
        this.bookTag = bookTag;
    }

    public List<Book> getBooks () {
        return books;
    }

    public void setBooks (List<Book> books) {
        this.books = books;
    }
}
