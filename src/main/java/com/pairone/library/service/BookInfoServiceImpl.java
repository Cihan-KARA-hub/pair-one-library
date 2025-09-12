package com.pairone.library.service;

import com.pairone.library.dto.book.BookCreateReq;
import com.pairone.library.dto.bookinfo.BookInfoCreateDto;
import com.pairone.library.entity.BookInfo;
import com.pairone.library.mapper.BookInfoMapper;
import com.pairone.library.repository.BookInfoRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class BookInfoServiceImpl {
    private final BookInfoRepository bookInfoRepository;
    private final BookInfoMapper bookInfoMapper;

    public BookInfoServiceImpl(BookInfoRepository bookInfoRepository, BookInfoMapper bookInfoMapper) {
        this.bookInfoRepository = bookInfoRepository;
        this.bookInfoMapper = bookInfoMapper;
    }

    public BookInfo save(BookInfoCreateDto bookInfo) {
        BookInfo info = bookInfoMapper.GetDtoToEntity(bookInfo);
         bookInfoRepository.save(info);
    }

    public BookInfoCreateDto getBookInfo(Integer bookId) throws ChangeSetPersister.NotFoundException {
        BookInfo entity = bookInfoRepository.findById(bookId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return bookInfoMapper.entityToDto(entity);
    }
}
