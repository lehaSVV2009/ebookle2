package com.ebookle.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
@Table(name = "Prefer")
public class Prefer implements Entity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mark")
    private int mark;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public int getMark () {
        return mark;
    }

    public void setMark (int mark) {
        this.mark = mark;
    }

    public Book getBook () {
        return book;
    }

    public void setBook (Book book) {
        this.book = book;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }
}
