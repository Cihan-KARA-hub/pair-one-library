package com.pairone.library.service;

import com.pairone.library.dto.book.BookCreateReq;
import com.pairone.library.dto.book.BookCreateRes;
import com.pairone.library.dto.book.BookListDto;
import com.pairone.library.dto.book.BookUpdateReq;
import com.pairone.library.entity.Book;
import com.pairone.library.mapper.BookMapper;
import com.pairone.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookCreateRes create(BookCreateReq req) {
        Book book = bookRepository.save(bookMapper.BookCreateReqToEntity(req));
        return new BookCreateRes(book.getName(), "Book Created");
    }

    public void update(BookUpdateReq book) {
        bookRepository.findById(book.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found "));
        Book bookEntity = bookMapper.BookUpdateReqToEntity(book);
        bookRepository.save(bookEntity);
    }

    public String delete(Integer id) {
        bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found "));
        bookRepository.deleteById(id);
        return "Book Deleted" + id;
    }

    public List<BookListDto> getAll(int size ,int page ) {
        Pageable pageable = PageRequest.of(size,page);
        Page<Book> books =bookRepository.findAll(pageable);
        return books.stream().map(bookMapper::BookListDtoMap).toList();

    }
}
