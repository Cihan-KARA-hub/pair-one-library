package com.pairone.library.service;
import com.pairone.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.pairone.library.dto.author.AuthorDto;
import com.pairone.library.entity.Author;
//import com.pairone.library.repository.AuthorRepository;

@Service
public class AuthorServiceImplement implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImplement(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto dto) {
        Author author = new Author();
        author.setFirstname(dto.getFirstname());
        author.setLastname(dto.getLastname());
        Author saved = authorRepository.save(author);
        return mapToDto(saved);
    }

    @Override
    public AuthorDto updateAuthor(Integer id, AuthorDto dto) {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setFirstname(dto.getFirstname());
        author.setLastname(dto.getLastname());
        Author updated = authorRepository.save(author);
        return mapToDto(updated);
    }

    @Override
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDto getAuthorById(Integer id) {
        return authorRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private AuthorDto mapToDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(author.getAuthorId());
        dto.setFirstname(author.getFirstname());
        dto.setLastname(author.getLastname());
        return dto;
    }
}
