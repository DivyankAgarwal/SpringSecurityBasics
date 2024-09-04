package com.example.springsecurityudemy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
public class AccountTransactions {

    @Id
    private String transactionId;

    private long accountNumber;

    private int customerID;

    private Date transactionDt;

    private String transactionSummary;

    private String transactionType;

    private int transactionAmt;

    private int closingBalance;

    private String createDt;


}
