package com.frzlyv.graphql_books.book;

import com.frzlyv.graphql_books.author.Author;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Book
 */
@Entity
@Getter
@Setter
@Builder
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
  private Long id;

  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  private Author author;

}
