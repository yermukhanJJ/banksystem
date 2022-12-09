package com.example.banksystem.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_users")
    private Long idUsers;

    @Column(name = "id_limits")
    private Long idLimits;

    @Column(name = "remaining_limit")
    private double remainingLimit;

    @Column(name = "date_of_the_transaction")
    private LocalDateTime dateOfTheTransaction;

    @Column(name = "transactional_sum")
    private double transactionalSum;

    @Column(name = "limit_exceeded")
    private boolean limitExceeded;

    @Column(name = "category")
    private String category;

    public Transactions(Long idLimits, Long idUsers, double remainingLimit, LocalDateTime dateOfTheTransaction, double transactionalSum, boolean limitExceeded, String category) {
        this.idLimits = idLimits;
        this.idUsers = idUsers;
        this.remainingLimit = remainingLimit;
        this.dateOfTheTransaction = dateOfTheTransaction;
        this.transactionalSum = transactionalSum;
        this.limitExceeded = limitExceeded;
        this.category = category;
    }
}
