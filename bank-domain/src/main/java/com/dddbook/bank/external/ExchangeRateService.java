package com.dddbook.bank.external;

import com.dddbook.bank.types.Currency;
import com.dddbook.bank.types.ExchangeRate;

/**
 * 抽象第三方服务
 */
public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);
}
