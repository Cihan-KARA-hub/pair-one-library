package com.pairone.library.service;

import com.pairone.library.dto.author.AuthorDto;
import com.pairone.library.dto.book.BookCreateReq;
import com.pairone.library.dto.book.BookCreateRes;
import com.pairone.library.dto.book.BookListDto;
import com.pairone.library.dto.book.BookUpdateReq;
import com.pairone.library.entity.*;
import com.pairone.library.mapper.AuthorMapper;
import com.pairone.library.mapper.BookInfoMapper;
import com.pairone.library.mapper.BookMapper;
import com.pairone.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookInfoServiceImpl bookInfoService;
    private final BookInfoMapper bookInfoMapper;
    private final PublisherService publisherService;
    private final CategoryService categoryService;


    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           AuthorService authorService,
                           AuthorMapper authorMapper,
                           BookInfoServiceImpl bookInfoService,
                           BookInfoMapper bookInfoMapper,
                           PublisherService publisherService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.bookInfoService = bookInfoService;
        this.bookInfoMapper = bookInfoMapper;
        this.publisherService = publisherService;

        this.categoryService = categoryService;
    }

    @Transactional
    public BookCreateRes create(BookCreateReq req) {
        List<AuthorDto> authorDtos = req.getAuthorId().stream()
                .map(authorService::getAuthorById)
                .toList();
        List<Author> authors = authorMapper.mapToEntityList(authorDtos);
        BookInfo bookInfo = bookInfoService.save(req.getBookinfoId());
        Publisher publisher = publisherService.bookServiceGetPublisher(req.getPublisherId());
        Category category = categoryService.getCategoryId(req.getCategoryId());
        bookRepository.save(bookMapper.BookCreateReqToEntity(req, bookInfo, publisher, authors, category));
        return new BookCreateRes(req.getName(), "Book Created");
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

    public List<BookListDto> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(size, page);
        Page<Book> books = bookRepository.findAll(pageable);
        return books.stream().map(bookMapper::BookListDtoMap).toList();

    }
}
