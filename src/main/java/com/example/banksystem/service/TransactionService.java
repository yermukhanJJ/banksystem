package com.example.banksystem.service;

import com.example.banksystem.model.Exchanges;
import com.example.banksystem.model.Limits;
import com.example.banksystem.model.Transactions;
import com.example.banksystem.model.Users;
import com.example.banksystem.payload.TransactionRequest;
import com.example.banksystem.repository.ExchangeRepository;
import com.example.banksystem.repository.LimitRepository;
import com.example.banksystem.repository.TransactionRepository;
import com.example.banksystem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LimitRepository limitRepository;
    private final UsersRepository usersRepository;
    private final ExchangeRepository exchangeRepository;
    private final ExchangeService exchangeService;

    public Transactions addtransaction(TransactionRequest request, String category) throws ParseException, IOException {
        Users userFrom = usersRepository.findByAccount(request.getAccountFrom())
                .orElseThrow(() -> new EntityNotFoundException("User with account: " + request.getAccountFrom() + " not found!"));

        Users userTo = usersRepository.findByAccount(request.getAccountTo())
                .orElseThrow(() -> new EntityNotFoundException("User with account: " + request.getAccountTo() + " not found!"));

        if (!limitRepository.existsByidUsers(userFrom.getId()))
            limitRepository.save(new Limits(userFrom.getId(), LocalDateTime.now(), 0));

        Double countTransactionSum = transactionRepository.CountTransactionalSum(userFrom.getId());

        if (!transactionRepository.existsByidUsers(userFrom.getId()))
            countTransactionSum = 0.0d;

        //Конвентируем входную сумму на USD
        Double sumUSD = request.getSum() / exchangeRate(LocalDate.now());

        Double remainingLimit = limitRepository.lastLimitSum(userFrom.getId()) - countTransactionSum - sumUSD;

        Long id_limit = limitRepository.findLastLimit(userFrom.getId())
                .orElseThrow(() -> new EntityNotFoundException("Limit not found!")).getId();

        Boolean limit_exceeded = false;

        if (remainingLimit < 0)
            limit_exceeded = true;

        return transactionRepository.save(new Transactions(id_limit, userFrom.getId(), remainingLimit, LocalDateTime.now(), sumUSD, limit_exceeded, category));
    }

    public Double exchangeRate(LocalDate date) throws ParseException, IOException {
        if (exchangeRepository.existsByDatetime(date)) { //Проверим базу на наличий данных на текущую дату

            //Получим из базы информацию валют на текущую дату
            Exchanges exchange = exchangeRepository.findByDatetime(date)
                    .orElseThrow(() -> new EntityNotFoundException("This date: " + date + " Exchange not found"));
            if (exchange.isMarketOpen()) { //Если маркет открыть, то возвращаем курс закрытия
                return exchange.getClose();
            } else return exchange.getPreviousClose(); //Если маркет закрыть, то возвращаем курс прошлого закрытия
        } else { //Если в базе нет данных на текущую дату то получим данные из API и сохраняем в базу
            Exchanges exchanges = exchangeService.saveExchange();
            if (exchanges.isMarketOpen()) {
                return exchanges.getClose();
            } else return exchanges.getPreviousClose();
        }
    }

}
