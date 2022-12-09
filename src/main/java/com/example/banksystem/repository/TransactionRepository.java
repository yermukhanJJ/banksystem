package com.example.banksystem.repository;

import com.example.banksystem.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query(value = "select sum(transactional_sum) from transactions where id_users = ?1", nativeQuery = true)
    Double CountTransactionalSum(Long id_user);

    Boolean existsByidUsers(Long id_user);

    @Query(value = "select * from transactions t where t.limit_exceeded = true and id_limits = " +
            "(select l.id from limits l where l.limit_sum = ?1 and l.id_users = ?2 LIMIT 1 )", nativeQuery = true)
    Optional<List<Transactions>> getTransactionLimitExceeded(double limit_sum, Long id_user);

    @Query(value = "select * from transactions where id_users = ?1", nativeQuery = true)
    Optional<List<Transactions>> getAllTransactions(Long id_user);

}
