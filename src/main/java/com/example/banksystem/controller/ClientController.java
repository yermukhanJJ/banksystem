package com.example.banksystem.controller;

import com.example.banksystem.model.Limits;
import com.example.banksystem.model.Transactions;
import com.example.banksystem.payload.LimitExceededRequest;
import com.example.banksystem.payload.LimitSetRequest;
import com.example.banksystem.service.ClientService;
import com.example.banksystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client-service/v1")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    //Установка лимита
    @PostMapping("/setlimit")
    public ResponseEntity<Limits> setLimit(@RequestBody LimitSetRequest request) {
        return ResponseEntity.ok(clientService.setLimit(request));
    }

    //Выводить все транзакзаций пользователя
    @GetMapping("/transactions")
    public ResponseEntity<List<Transactions>> getAllTransactions(@RequestParam int account) {
        return ResponseEntity.ok(clientService.getAllTransactions(account));
    }

    //Получить все транзакций превысивших лимит
    @GetMapping("/limit-exceeded")
    public ResponseEntity<List<Transactions>> getExceededLimit(@RequestBody LimitExceededRequest request) {
        return ResponseEntity.ok(clientService.getTransactionsByLimitExceeded(request));
    }

    //Получить список всех лимитов
    @GetMapping("limits")
    public ResponseEntity<List<Limits>> getAllLimits(@RequestParam int account) {
        return ResponseEntity.ok(clientService.getAllLimits(account));
    }

}
