package com.frzlyv.graphql_books.book;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.frzlyv.graphql_books.author.Author;
import com.frzlyv.graphql_books.author.AuthorRepository;

import lombok.RequiredArgsConstructor;

/**
 * BookController
 */
@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

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

  @MutationMapping
  public Book addBook(@Argument BookInput bookInput) {
    Author author = authorRepository.findById(bookInput.authorId())
        .orElseThrow();

    Book book = Book.builder()
        .title(bookInput.title())
        .author(author)
        .build();

    return bookRepository.save(book);
  }

}
