package com.example.banksystem.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Exchanges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "datetime")
    private LocalDate datetime;

    @Column(name = "is_market_open")
    private boolean isMarketOpen;

    @Column(name = "closerate")
    private double close;

    @Column(name = "previous_close")
    private double previousClose;

    public Exchanges(String symbol, String name, String exchange, LocalDate datetime, boolean isMarketOpen, double close, double previousClose) {
        this.symbol = symbol;
        this.name = name;
        this.exchange = exchange;
        this.datetime = datetime;
        this.isMarketOpen = isMarketOpen;
        this.close = close;
        this.previousClose = previousClose;
    }

}
