package com.frzlyv.graphql_books.book;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

/**
 * BookController
 */
@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookRepository bookRepository;

  // @SchemaMapping(typeName = "Query", field = "books")
  // @QueryMapping(name = "books")
  @QueryMapping
  public List<Book> books() {
    return bookRepository.findAll();
  }

  @QueryMapping
  public Optional<Book> book(@Argument Long id) {
    return bookRepository.findById(id);
  }

}
