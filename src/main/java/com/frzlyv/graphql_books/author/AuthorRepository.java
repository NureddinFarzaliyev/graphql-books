package com.frzlyv.graphql_books.author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthorRepository
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

  List<Author> findAllByNameContainsIgnoreCase(String text);

}
