package com.example.banksystem.repository;

import com.example.banksystem.model.Limits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limits, Long> {
    Boolean existsByidUsers(Long idUser);

    @Query(value = "select limit_sum from limits where id_users = ?1 " +
            "and id = ( select MAX(id) from limits)", nativeQuery = true)
    Double lastLimitSum(Long idUser);

    @Query(value = "select * from limits where id_users = ?1 " +
            "and id = ( select MAX(id) from limits)", nativeQuery = true)
    Optional<Limits> findLastLimit(Long idUser);

    @Query(value = "select * from limits where id_users = ?1", nativeQuery = true)
    Optional<List<Limits>> getAllLimits(Long id_user);

}
