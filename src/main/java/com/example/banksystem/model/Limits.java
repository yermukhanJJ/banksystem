package com.example.banksystem.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "limits")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Limits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_users")
    private Long idUsers;

    @Column(name = "set_limit_time")
    private LocalDateTime setLimitTime;

    @Column(name = "limit_sum")
    private double limitSum;

    public Limits(Long id_users, LocalDateTime setLimitTime, double limitSum) {
        this.idUsers = id_users;
        this.setLimitTime = setLimitTime;
        this.limitSum = limitSum;
    }

}
