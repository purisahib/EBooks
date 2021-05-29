package com.pradeep.new014.ui.contener;

public class Book {
    private String Title;
    private String Category;
    private String Description;
    private String Thumbnail;
    private String Author;
    private String Bookl;

    public Book() {
    }

    public Book(String title, String category, String description, String thumbnail, String author, String bookl) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        Author = author;
        Bookl = bookl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getBookl() {
        return Bookl;
    }

    public void setBookl(String bookl) {
        Bookl = bookl;
    }
}
