package com.example.grapthql.model;

import javax.persistence.*;

@Entity
public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String isbn;
        private String title;
        private String publisher;

    public Book() {
    }

    public Book(String isbn, String title, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
