package com.example.exchange_pattaya.mapper;

import com.example.exchange_pattaya.dto.AllFindDto;
import com.example.exchange_pattaya.dto.CurrencyDto;
import com.example.exchange_pattaya.dto.CurrencyDtoList;
import com.example.exchange_pattaya.entity.Currency;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto convertToDto(Currency currency);

    AllFindDto convertToAllFinDto(Currency currency);

    Currency convertToEntity(CurrencyDto currencyDto);

    default CurrencyDtoList responseList(List<Currency> currencyList) {
        CurrencyDtoList response = new CurrencyDtoList();
        response.setCurrencies(currencyList.stream().map(this::convertToAllFinDto).collect(Collectors.toList()));
        return response;
    }

}
