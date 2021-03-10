package com.dddbook.bank.types;

import java.math.BigDecimal;

public class Money {
    private BigDecimal amount; //金额
    private Currency currency; //币种
    public Money() {
    }
    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean lessThen(Money money) {
        return amount.compareTo(money.getAmount()) < 0;
    }

    public Money subtract(Money money) {
        Money m = new Money();
        m.setAmount(this.getAmount().subtract(money.getAmount()));
        m.setCurrency(this.getCurrency());
        return m;
    }

    public Money add(Money money) {
        Money m = new Money();
        m.setAmount(this.getAmount().add(money.getAmount()));
        m.setCurrency(this.getCurrency());
        return m;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
