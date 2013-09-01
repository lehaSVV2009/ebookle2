package com.ebookle.webmodel;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 30.08.13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
public class BookCreationForm {

    private String title;
    private String description;
    private Integer category;
    private String bookTag;

    public String getBookTag () {
        return bookTag;
    }

    public void setBookTag (String bookTag) {
        this.bookTag = bookTag;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public Integer getCategory () {
        return category;
    }

    public void setCategory (Integer category) {
        this.category = category;
    }
}
