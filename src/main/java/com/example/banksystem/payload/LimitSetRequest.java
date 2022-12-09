package com.example.banksystem.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LimitSetRequest {

    private Long id_user;

    private double limitSum;

}
