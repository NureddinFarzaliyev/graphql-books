package com.frzlyv.graphql_books;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.frzlyv.graphql_books.author.Author;
import com.frzlyv.graphql_books.author.AuthorRepository;
import com.frzlyv.graphql_books.book.Book;
import com.frzlyv.graphql_books.book.BookRepository;

import lombok.RequiredArgsConstructor;

/**
 * DataLoader
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  private record AuthorsAndBooks(
      Map<String, Author> authors,
      Map<String, Book> books) {
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Loading data...");
    loadAuthorsAndBooks();
  }

  private AuthorsAndBooks loadAuthorsAndBooks() {
    Author josh = Author.builder()
        .name("Josh Long")
        .build();
    authorRepository.save(josh);

    Author greg = Author.builder()
        .name("Greg Turnquist")
        .build();
    authorRepository.save(greg);

    Author mark = Author.builder()
        .name("Mark Heckler")
        .build();
    authorRepository.save(mark);

    Book reactiveSpring = Book.builder()
        .title("Reactive Spring")
        .author(josh)
        .build();
    bookRepository.save(reactiveSpring);

    Book cloudNativeSpring = Book.builder()
        .title("Cloud Native Spring")
        .author(greg)
        .build();
    bookRepository.save(cloudNativeSpring);

    Book learningSpring = Book.builder()
        .title("Learning Spring Boot 3.0")
        .author(mark)
        .build();
    bookRepository.save(learningSpring);

    Book springBootInAction = Book.builder()
        .title("Spring Boot in Action")
        .author(mark)
        .build();
    bookRepository.save(springBootInAction);

    return new AuthorsAndBooks(
        Map.of(
            "josh", josh,
            "greg", greg,
            "mark", mark),
        Map.of(
            "reactiveSpring", reactiveSpring,
            "cloudNativeSpring", cloudNativeSpring,
            "learningSpring", learningSpring,
            "springBootInAction", springBootInAction));
  }

}
