package com.dddbook.bank.persistence;

import com.dddbook.bank.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountDO selectByAccountNumber(String number) throws Exception {
        if ("100".equals(number)){
            return new AccountDO(
                    1,
                    new AccountId(10L),
                    new AccountNumber("100"),
                    new UserId(1L),
                    new  Money(new BigDecimal(1000), Currency.CNY),
                    new  Money(new BigDecimal(50000), Currency.CNY)
            );
        }else {
            log.error("null data");
            return null;
        }
    }

    @Override
    public AccountDO selectByUserId(Long id) throws Exception {
        if (10 == id){
            return new AccountDO(
                    1,
            new AccountId(10L),
            new AccountNumber("100"),
            new UserId(1L),
            new  Money(new BigDecimal(1000), Currency.CNY),
            new  Money(new BigDecimal(50000), Currency.CNY)
            );
        }else {
            log.error("null data");
            return null;
        }
    }

    @Override
    public AccountDO selectById(Long id) {
        System.out.println("selectById");
        return null;
    }

    @Override
    public void insert(AccountDO accountDO) {
        System.out.println(">>> insert data success!");
    }

    @Override
    public void update(AccountDO accountDO) {
        System.out.println(">>> update data success!");
    }
}
