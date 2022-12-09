package com.example.banksystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LimitExceededRequest {

    private int account;

    private double limit_sum;

}
