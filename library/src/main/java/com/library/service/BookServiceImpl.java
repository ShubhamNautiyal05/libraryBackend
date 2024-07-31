package com.library.service;

import com.library.entity.Book;
import com.library.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        if (CollectionUtils.isEmpty(allBooks)){
            throw new IllegalArgumentException("No books found");
        } else {
            return allBooks;
        }
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No book found"));
    }

    @Override
    public Book saveBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Books details not save. Please try again");
        }
    }

    @Override
    public String deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return "Book record delete successfully";
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Some error occurred while delete the record");
        }
    }
}