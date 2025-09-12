package com.pairone.library.mapper;

import com.pairone.library.dto.book.*;
import com.pairone.library.entity.*;
import com.pairone.library.service.AuthorService;
import com.pairone.library.service.PublisherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public BookMapper(PublisherService publisherService, AuthorService authorService) {
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

    public Book BookCreateReqToEntity(BookCreateReq req,
                                      BookInfo bookInfo,
                                      Publisher publisher,
                                      List<Author> authors,
                                      Category category) {
        Book book = new Book();
        book.setName(req.getName());
        book.setPageCount(req.getPageCount());
        book.setEditionNo(req.getEditionNo());
        book.setBookinfoId(bookInfo);
        book.setPublisher(publisher);
        book.setCategory(category);
        book.setAuthors(authors);
        return book;
    }

    public Book BookUpdateReqToEntity(BookUpdateReq bookUpdateReq) {
        Book book = new Book();
        book.setId(bookUpdateReq.getId());
        book.setBookinfoId(bookUpdateReq.getBookinfoId());
        book.setEditionNo(bookUpdateReq.getEditionNo());
        book.setPublisher(bookUpdateReq.getPublisherId());
        book.setCategory(bookUpdateReq.getCategoryId());
        return book;

    }

    public BookListDto BookListDtoMap(Book book) {
        BookListDto bookListDto = new BookListDto();
        bookListDto.setName(book.getName());
        bookListDto.setEditionNo(book.getEditionNo());
        bookListDto.setPageCount(book.getPageCount());
        bookListDto.setId(book.getId());
        bookListDto.setPublisher(new BookListPublisherDto(book.getPublisher().getName()));
        bookListDto.setAuthors(
                book.getAuthors()
                        .stream()
                        .map(Author::getFirstname)
                        .toList()
        );
        BookListBookInfoDto bookListBookInfoDto = new BookListBookInfoDto();
        bookListBookInfoDto.setIsbn(book.getBookinfoId().getIsbn());
        bookListBookInfoDto.setTitle(book.getBookinfoId().getTitle());
        bookListBookInfoDto.setStatus(book.getBookinfoId().getStatus());
        bookListBookInfoDto.setType(book.getBookinfoId().getType());
        bookListBookInfoDto.setLanguage(book.getBookinfoId().getLanguage());
        bookListBookInfoDto.setCondition(book.getBookinfoId().getCondition());
        bookListBookInfoDto.setCopyCount(book.getBookinfoId().getCopyCount());
        bookListBookInfoDto.setLocation(book.getBookinfoId().getLocation());
        bookListDto.setBookinfoId(bookListBookInfoDto);

        bookListDto.setCategoryName(book.getCategory().getName());

        return bookListDto;
    }
}
