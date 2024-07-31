package com.library.repo;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Long> {


    @Query("select b from Book b where b.isbn = ?1 and b.issued = ?2")
    Optional<Book> findByIsbnAndIssued(String isbn, boolean issued);
}
