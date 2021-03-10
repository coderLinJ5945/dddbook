package com.dddbook.bank.application;

import com.dddbook.bank.application.result.ResponseEntity;

import java.math.BigDecimal;

public interface TransferService {
    ResponseEntity<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, Integer targetCurrencyId) throws Exception;
}
