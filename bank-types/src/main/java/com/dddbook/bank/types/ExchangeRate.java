package com.dddbook.bank.types;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 汇率
 * ExchangeRate来封装汇率计算逻辑
 */
public class ExchangeRate {
    private BigDecimal rate; //汇率
    private Currency from;   //原币种
    private Currency to;     //转换币种

    public ExchangeRate(BigDecimal rate, Currency from, Currency to) {
        this.rate = rate;
        this.from = from;
        this.to = to;
    }

    /**
     * 汇率转换
     * @param fromMoney
     * @return
     */
    public Money exchange(Money fromMoney) {
        Objects.nonNull(fromMoney);
        BigDecimal targetAmount = fromMoney.getAmount().divide(rate);
        return new Money(targetAmount, to);
    }
}
