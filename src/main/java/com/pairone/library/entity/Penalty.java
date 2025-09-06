package com.pairone.library.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "penalty")
public class Penalty {
    /* penalty_id int
 member_id int
 loan_id int
 penalty_type varchar
 delay_days int
 is_returned boolean
 amount decimal
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalty_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(name = "penalty_type", length = 50, nullable = false)
    private String penaltyType;

    @Column(name = "delay_days")
    private Integer delayDays;

    @Column(name = "is_returned", nullable = false)
    private boolean isReturned;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;
}
