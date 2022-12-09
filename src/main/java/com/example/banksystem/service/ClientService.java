package com.example.banksystem.service;

import com.example.banksystem.model.Limits;
import com.example.banksystem.model.Transactions;
import com.example.banksystem.model.Users;
import com.example.banksystem.payload.LimitExceededRequest;
import com.example.banksystem.payload.LimitSetRequest;
import com.example.banksystem.repository.LimitRepository;
import com.example.banksystem.repository.TransactionRepository;
import com.example.banksystem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final UsersRepository usersRepository;
    private final LimitRepository limitRepository;
    private final TransactionRepository transactionRepository;

    public Limits setLimit(LimitSetRequest request) {
        Users user1 = usersRepository.findById(request.getId_user())
                .orElseThrow(() -> new EntityNotFoundException("User with id" + request.getId_user() + "not found!"));

        return limitRepository.save(new Limits(user1.getId(), LocalDateTime.now(), request.getLimitSum()));
    }

    public List<Transactions> getTransactionsByLimitExceeded(LimitExceededRequest request) {
        Users user = usersRepository.findByAccount(request.getAccount())
                .orElseThrow(() -> new EntityNotFoundException("User with account: " + request.getAccount() + " not found!"));

        return transactionRepository.getTransactionLimitExceeded(request.getLimit_sum(), user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Transactions not found!"));
    }

    public List<Transactions> getAllTransactions(int account) {
        Users user = usersRepository.findByAccount(account)
                .orElseThrow(() -> new EntityNotFoundException("User with account: " + account + " not found!"));

        return transactionRepository.getAllTransactions(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Transactions not found!"));
    }

    public List<Limits> getAllLimits(int account) {
        Users user = usersRepository.findByAccount(account)
                .orElseThrow(() -> new EntityNotFoundException("User with account: " + account + " not found!"));

        return limitRepository.getAllLimits(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Limit not found!"));
    }

}
