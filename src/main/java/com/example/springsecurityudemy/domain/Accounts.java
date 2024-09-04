package com.example.springsecurityudemy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Accounts {



    @Id
    private long accountNumber;

    private int customerID;

    private String accountType;

    private String branchAddress;

    private String createDt;
    

}
