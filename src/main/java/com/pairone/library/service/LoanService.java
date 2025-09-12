package com.pairone.library.service;

import com.pairone.library.dto.loan.*;
import com.pairone.library.entity.Loan;
import com.pairone.library.entity.Book;
import com.pairone.library.entity.Member;
import com.pairone.library.mapper.LoanMapper;
import com.pairone.library.repository.LoanRepository;
import com.pairone.library.repository.BookRepository;
import com.pairone.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public LoanResponseDto createLoan(LoanCreateDto dto) {
        Loan loan = new Loan();
        Book book = bookRepository.findById(bookR.getBookId())
                .orElseThrow(() -> new RuntimeException("Book bulunamadı: " + dto.getBookId()));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member bulunamadı: " + dto.getMemberId()));

        loan.setBook(book);
        loan.setMember(member);
        loan.setRequestDate(dto.getRequestDate());
        loan.setDueDate(dto.getDueDate());
        loan.setStatus(dto.getStatus());

        Loan saved = loanRepository.save(loan);
        return mapToResponseDto(saved);
    }

    public List<LoanListDto> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(this::mapToListDto)
                .collect(Collectors.toList());
    }

    public LoanResponseDto getLoanById(int id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan bulunamadı: " + id));
        return mapToResponseDto(loan);
    }

    public LoanResponseDto updateLoan(Integer id, LoanUpdateDto dto) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan bulunamadı: " + id));

        if (dto.getDueDate() != null) loan.setDueDate(dto.getDueDate());
        if (dto.getReturnDate() != null) loan.setReturnDate(dto.getReturnDate());
        if (dto.getStatus() != null) loan.setStatus(dto.getStatus());

        Loan updated = loanRepository.save(loan);
        return mapToResponseDto(updated);
    }

    public void deleteLoan(Long id, LoanDeleteDto dto) {
        if (!loanRepository.existsById(id)) {
            throw new RuntimeException("Loan bulunamadı: " + id);
        }
        loanRepository.deleteById(id);
    }

    private LoanResponseDto mapToResponseDto(Loan loan) {
        LoanResponseDto dto = new LoanResponseDto();
        dto.setId(loan.getId());
        dto.setBookId(loan.getBook().getId());
        dto.setBookTitle(loan.getBook().getTitle());
        dto.setMemberId(loan.getMember().getId());
        dto.setMemberName(loan.getMember().getName());
        dto.setRequestDate(loan.getRequestDate());
        dto.setDueDate(loan.getDueDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setStatus(loan.getStatus());
        return dto;
    }

    private LoanListDto mapToListDto(Loan loan) {
        LoanListDto dto = new LoanListDto();
        dto.setId(loan.getId());
        dto.setBookTitle(loan.getBook().getTitle());
        dto.setMemberName(loan.getMember().getName());
        dto.setRequestDate(loan.getRequestDate());
        dto.setDueDate(loan.getDueDate());
        dto.setStatus(loan.getStatus());
        return dto;
    }
}
