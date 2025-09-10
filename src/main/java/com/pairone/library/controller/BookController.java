package com.pairone.library.controller;

import com.pairone.library.dto.book.BookCreateReq;
import com.pairone.library.dto.book.BookCreateRes;
import com.pairone.library.dto.book.BookListDto;
import com.pairone.library.dto.book.BookUpdateReq;
import com.pairone.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    //Create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookCreateRes createBook(@RequestBody BookCreateReq book) {
        return bookService.create(book);
    }

    //update
    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBook(@RequestBody BookUpdateReq book) {
        bookService.update(book);
    }

    //delete
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer bookId) {
        return bookService.delete(bookId);
    }

    //Get
    @GetMapping
    public List<BookListDto> getAllBooks(@RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "0") int page) {
        return bookService.getAll(size, page);
    }
}
