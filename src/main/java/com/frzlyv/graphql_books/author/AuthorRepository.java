package com.frzlyv.graphql_books.author;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthorRepository
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
