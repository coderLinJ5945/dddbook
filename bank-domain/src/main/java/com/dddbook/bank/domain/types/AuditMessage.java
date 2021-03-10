package com.dddbook.bank.domain.types;

import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.types.AccountNumber;
import com.dddbook.bank.types.Money;
import com.dddbook.bank.types.UserId;
import lombok.Data;

import java.util.Date;

/**
 * 消息载体
 */
@Data
public class AuditMessage {
    private UserId userId;
    private AccountNumber source;
    private AccountNumber target;
    private Money money;
    private Date date;

    public AuditMessage(UserId userId, AccountNumber sourceAccountNumber, AccountNumber targetAccountNumber, Money targetMoney) {
        this.userId = userId;
        this.source = sourceAccountNumber;
        this.target = targetAccountNumber;
        this.money = targetMoney;
        this.date = new Date();
    }

    public String serialize() {
        return userId.toString() + "," + source.toString() + "," + target.toString() + "," + money.toString() + "," + date.toString();
    }

    public static AuditMessage deserialize(String value) {
        // todo
        return null;
    }
}
