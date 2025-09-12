package com.pairone.library.service;
import java.util.List;
import com.pairone.library.dto.author.AuthorDto;
import com.pairone.library.entity.Member;
import com.pairone.library.entity.Author;
import com.pairone.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto dto);
    AuthorDto updateAuthor(Integer id, AuthorDto dto);
    void deleteAuthor(Integer id);
    AuthorDto getAuthorById(Integer id);
    List<AuthorDto> getAllAuthors();

}
