package com.example.grapthql.repository;

import com.example.grapthql.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findById(int id);
}

