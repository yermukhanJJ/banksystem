package com.example.banksystem.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionRequest {

    private int accountFrom;

    private int accountTo;

    private String currencyShortname;

    private double sum;

}
