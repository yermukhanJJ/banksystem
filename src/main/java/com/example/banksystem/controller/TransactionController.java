package com.example.banksystem.controller;

import com.example.banksystem.model.Transactions;
import com.example.banksystem.payload.TransactionRequest;
import com.example.banksystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/bank-service/v1")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    //Прием транзакций
    @PostMapping("/transaction")
    public ResponseEntity<Transactions> transaction(@RequestBody TransactionRequest request, @RequestParam String category) throws ParseException, IOException {
        if (!category.equals("product") && !category.equals("service"))
            return ResponseEntity.notFound().build();

        Transactions transaction = transactionService.addtransaction(request, category);

        return ResponseEntity.ok(transaction);
    }

}
