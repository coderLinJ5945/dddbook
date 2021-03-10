package com.dddbook.bank.controller;

import com.dddbook.bank.application.TransferService;
import com.dddbook.bank.application.result.ResponseEntity;
import com.dddbook.bank.types.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequestMapping("/api/bank/v1")
@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping("/")
    public String transfer(){
        return "hello";
    }

    @GetMapping("/transfer")
    public ResponseEntity<Boolean> transfer(@RequestParam("number") String targetAccountNumber, @RequestParam("amount") BigDecimal amount, @RequestParam("userId") Long userId) throws Exception {
        return transferService.transfer(userId, targetAccountNumber, amount, Currency.CNY.getId());
    }
}
