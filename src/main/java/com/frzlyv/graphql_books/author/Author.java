package com.frzlyv.graphql_books.author;

import java.util.List;

import com.frzlyv.graphql_books.book.Book;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author
 */
@Entity
@Getter
@Setter
@Builder
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  private List<Book> books;

}
