package com.frzlyv.graphql_books.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.frzlyv.graphql_books.author.Author;
import com.frzlyv.graphql_books.author.AuthorRepository;
import com.frzlyv.graphql_books.book.Book;
import com.frzlyv.graphql_books.book.BookRepository;

import lombok.RequiredArgsConstructor;

/**
 * SearchController
 */
@Controller
@RequiredArgsConstructor
public class SearchController {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @QueryMapping
  public List<Object> search(@Argument String text) {

    List<Object> results = new ArrayList<>();

    List<Author> authors = authorRepository.findAllByNameContainsIgnoreCase(text);
    results.addAll(authors);

    List<Book> books = bookRepository.findAllByTitleContainsIgnoreCase(text);
    results.addAll(books);

    return results;

  }

}
