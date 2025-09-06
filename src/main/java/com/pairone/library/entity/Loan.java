package com.pairone.library.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "Loan")
public class Loan {
    /* loan_id int
 book_id int
 member_id int
 staff_id int
 request_date date
 due_date date
 return_date date
 status varchar
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "request_date", nullable = false)
    private OffsetDateTime requestDate;

    @Column(name = "due_date", nullable = false)
    private OffsetDateTime dueDate;

    @Column(name = "return_date")
    private OffsetDateTime returnDate;

    @Column(name = "status", length = 20, nullable = false)
    private String status;
}
