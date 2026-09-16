package com.frzlyv.graphql_books.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BookRepository
 */
public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByTitleContainsIgnoreCase(String text);

  List<Book> findByAuthorIdIn(List<Long> authorIds);

}
