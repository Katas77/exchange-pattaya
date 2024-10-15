package com.example.exchange_pattaya.service;

import com.example.exchange_pattaya.getexchangerate.ExchangeRateService;
import com.example.exchange_pattaya.dto.CurrencyDto;
import com.example.exchange_pattaya.dto.CurrencyDtoList;
import com.example.exchange_pattaya.entity.Currency;
import com.example.exchange_pattaya.exception.CbrException;
import com.example.exchange_pattaya.mapper.CurrencyMapper;
import com.example.exchange_pattaya.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyMapper mapper;
    private final CurrencyRepository repository;
    private final ExchangeRateService exchangeService;

    public CurrencyDto getById(Long id) {
        log.info("CurrencyService method getById executed");
        Currency currency = repository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found with id: " + id));
        return mapper.convertToDto(currency);
    }

    public Double convertValue(Long value, Long numCode) {
        log.info("CurrencyService method convertValue executed");
        Currency currency = repository.findByIsoNumCode(numCode);
        return value * currency.getValue();
    }

    public CurrencyDto create(CurrencyDto dto) {
        log.info("CurrencyService method create executed");
        return  mapper.convertToDto(repository.save(mapper.convertToEntity(dto)));
    }
    public CurrencyDtoList findAll()  {
        return mapper.responseList(repository.findAll());
    }
    public void updateCurrency() throws CbrException {
        Currency USD=repository.findById(1333L).orElseThrow();
        USD.setValue(exchangeService.getUSDExchangeRate());
        repository.save(USD);
        Currency EUR=repository.findById(2333L).orElseThrow();
        EUR.setValue(exchangeService.getEURExchangeRate());
        repository.save(EUR);
        Currency CNY=repository.findById(3333L).orElseThrow();
        CNY.setValue(exchangeService.getCNYExchangeRate());
        repository.save(CNY);
        Currency GBP=repository.findById(4333L).orElseThrow();
        GBP.setValue(exchangeService.getGBPExchangeRate());
        repository.save(GBP);
        Currency GEL =repository.findById(5333L).orElseThrow();
        GEL.setValue(exchangeService.getGELExchangeRate());
        repository.save(GEL);
    }
    @PostConstruct
    private void updateCurrencyTimer() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                updateCurrency();
            } catch (CbrException e) {
                throw new RuntimeException(e);
            }
        }, 0, 1, TimeUnit.HOURS);
    }

}
