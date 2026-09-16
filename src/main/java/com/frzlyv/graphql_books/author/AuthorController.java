package com.frzlyv.graphql_books.author;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.frzlyv.graphql_books.book.Book;
import com.frzlyv.graphql_books.book.BookRepository;

import lombok.RequiredArgsConstructor;

/**
 * AuthorController
 */
@Controller
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  @QueryMapping
  List<Author> authors() {
    return authorRepository.findAll();
  }

  // @SchemaMapping
  // public List<Book> books(Author author) {
  // // This controller annotated with SchemaMapping and have name "books"
  // // will automatically replace the "books" field in "authors" query with
  // // the data returned from it
  //
  // // It creates N+1 problem
  // // And runs sequentially on single thread
  //
  // System.out.println("Reaching a microservice to get information about
  // books...");
  // return new ArrayList<>(); // temporary empty array
  // }

  @BatchMapping
  public List<List<Book>> books(List<Author> authors) {
    // This controller annotated with BatchMapping and have name "books"
    // will automatically replace the "books" field in "authors" query with
    // the data returned from it

    // It solves N+1 problem
    // And runs concurrently on multiple threads (because of the configuration in
    // application.properties)

    // Repository is used instead of microservice

    List<Long> authorIds = authors.stream().map(Author::getId).toList();
    List<Book> books = bookRepository.findByAuthorIdIn(authorIds);

    Map<Long, List<Book>> booksByAuthor = books.stream()
        .collect(Collectors.groupingBy(b -> b.getAuthor().getId()));

    return authors.stream()
        .map(a -> booksByAuthor.getOrDefault(a.getId(), new ArrayList<>()))
        .toList();
  }

}
