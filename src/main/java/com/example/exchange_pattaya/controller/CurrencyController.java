package com.example.exchange_pattaya.controller;

import com.example.exchange_pattaya.dto.CurrencyDto;
import com.example.exchange_pattaya.dto.CurrencyDtoList;
import com.example.exchange_pattaya.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping()
    public ResponseEntity<CurrencyDtoList> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CurrencyDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(value = "/convert")
    public ResponseEntity<Double> convertValue(@RequestParam("currencyCode") Long currencyCode) {
        return ResponseEntity.ok(service.convertValue(currencyCode));
    }

    @PostMapping("/create")
    public ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
