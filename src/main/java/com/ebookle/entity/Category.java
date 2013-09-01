package com.ebookle.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
@Table(name = "Category")
public class Category implements Entity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<Book>();

    @Override
    public Integer getId () {
        return id;
    }

    @Override
    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<Book> getBooks () {
        return books;
    }

    public void setBooks (List<Book> books) {
        this.books = books;
    }
}
