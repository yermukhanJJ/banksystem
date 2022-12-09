package com.example.banksystem.repository;

import com.example.banksystem.model.Exchanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchanges, Long> {
    Boolean existsByDatetime(LocalDate date);

    Optional<Exchanges> findByDatetime(LocalDate date);
}
