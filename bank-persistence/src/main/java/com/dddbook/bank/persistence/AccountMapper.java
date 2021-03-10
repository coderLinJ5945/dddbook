package com.dddbook.bank.persistence;

public interface AccountMapper {
    AccountDO selectByAccountNumber(String number) throws Exception;

    AccountDO selectByUserId(Long id) throws Exception;

    AccountDO selectById(Long id);

    void insert(AccountDO accountDO);

    void update(AccountDO accountDO);
}
