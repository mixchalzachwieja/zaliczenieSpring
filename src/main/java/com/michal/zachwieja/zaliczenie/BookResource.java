package com.michal.zachwieja.zaliczenie;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookResource {

    private final BookRepository bookRepository;

    public BookResource(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) Long id) {
        return Optional.ofNullable(id)
                .flatMap(bookRepository::findById)
                .map(Collections::singletonList)
                .orElseGet(bookRepository::findAll);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

}
