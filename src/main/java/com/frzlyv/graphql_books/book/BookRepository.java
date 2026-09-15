package com.frzlyv.graphql_books.book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BookRepository
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
