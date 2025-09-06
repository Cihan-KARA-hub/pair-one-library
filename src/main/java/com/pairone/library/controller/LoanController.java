package com.pairone.library.controller;

import com.pairone.library.dto.loan.*;
import com.pairone.library.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanListDto>> getAllLoans() {
        List<LoanListDto> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDto> getLoanById(@PathVariable Long id) {
        LoanResponseDto loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(@RequestBody LoanCreateDto loanCreateDto) {
        LoanResponseDto createdLoan = loanService.createLoan(loanCreateDto);
        return ResponseEntity.ok(createdLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponseDto> updateLoan(
            @PathVariable Long id,
            @RequestBody LoanUpdateDto loanUpdateDto) {
        LoanResponseDto updatedLoan = loanService.updateLoan(id, loanUpdateDto);
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(
            @PathVariable Long id,
            @RequestBody(required = false) LoanDeleteDto loanDeleteDto) {
        loanService.deleteLoan(id, loanDeleteDto);
        return ResponseEntity.ok("Loan kaydı silindi. ID: " + id);
    }
}
