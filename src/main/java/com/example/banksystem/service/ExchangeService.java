package com.example.banksystem.service;

import com.example.banksystem.model.Exchanges;
import com.example.banksystem.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public Exchanges saveExchange() throws ParseException, IOException {
        URL url = new URL("https://api.twelvedata.com/quote?symbol=USD/KZT&interval=1day&timezone=Asia/Almaty&apikey=8ae8dde5421e40ee952af10a6b68eaf4");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        //Чекать ответ
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {

            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }

            scanner.close();

            //Парсим ответ
            JSONParser parse = new JSONParser();
            JSONObject currencyData = (JSONObject) parse.parse(String.valueOf(informationString));

            //Парсим данные в соответствий типу полям сущности
            LocalDate localDate = LocalDate.parse((String) currencyData.get("datetime"));
            double close = Double.parseDouble((String) currencyData.get("close"));
            String symbol = (String) currencyData.get("symbol");
            String name = (String) currencyData.get("name");
            String exchange = (String) currencyData.get("exchange");
            boolean isMarketOpen = Boolean.parseBoolean(currencyData.get("is_market_open").toString());
            double previousClose = Double.parseDouble(currencyData.get("previous_close").toString());

            //Сохраняем в базу
            return exchangeRepository.save(new Exchanges(symbol, name, exchange, localDate, isMarketOpen, close, previousClose));
        }
    }
}

