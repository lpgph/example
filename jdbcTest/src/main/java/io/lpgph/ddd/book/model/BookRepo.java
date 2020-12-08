package io.lpgph.ddd.book.model;


import org.springframework.data.repository.CrudRepository;


public interface BookRepo extends CrudRepository<Book, Long> {

}
