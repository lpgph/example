package io.lpgph.ddd.book.model;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepo extends ReactiveCrudRepository<Book, Long> {

}
