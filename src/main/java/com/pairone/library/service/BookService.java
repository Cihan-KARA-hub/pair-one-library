package com.pairone.library.service;

import com.pairone.library.dto.book.BookCreateReq;
import com.pairone.library.dto.book.BookCreateRes;
import com.pairone.library.dto.book.BookListDto;
import com.pairone.library.dto.book.BookUpdateReq;

import java.util.List;

public interface BookService {
    BookCreateRes create(BookCreateReq req);

    void update(BookUpdateReq book);

    String delete(Integer id);

    List<BookListDto> getAll(int size, int page);}
