package com.dddbook.bank.external;

import com.dddbook.bank.types.Currency;
import com.dddbook.bank.types.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Override
    public ExchangeRate getExchangeRate(Currency source, Currency target) {
        log.info("get change rate from three server...");
        BigDecimal rate = new BigDecimal(1);
        log.info("get rate success! rate is {}", rate);
        return new ExchangeRate(rate, Currency.CNY, Currency.CNY);
    }
}
