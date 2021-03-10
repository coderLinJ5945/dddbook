package com.dddbook.bank.repository.impl;

import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.repository.AccountRepository;
import com.dddbook.bank.types.AccountId;
import com.dddbook.bank.types.AccountNumber;
import com.dddbook.bank.types.UserId;
import com.dddbook.bank.persistence.AccountDO;
import com.dddbook.bank.persistence.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.dddbook.bank.repository.impl.AccountBuilder.TRANSLATOR;

/**
 * AccountRepositoryImpl实现类，由于其职责被单一出来，
 * 只需要关注Account到AccountDO的映射关系和Repository方法到DAO方法之间的映射关系，相对于来说更容易测试。
 */
@Component
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountMapper accountDAO;

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = null;
        try {
            accountDO = accountDAO.selectByAccountNumber(accountNumber.getNumber());
        } catch (Exception e) {
            log.error("cant find the account by account number", e);
        }
        return TRANSLATOR.toAccount(accountDO);
    }

    @Override
    public Account find(UserId userId){
        AccountDO accountDO = null;
        try {
            accountDO = accountDAO.selectByUserId(userId.getId());
        } catch (Exception e) {
            log.error("cant find the account by userId", e);
        }
        return TRANSLATOR.toAccount(accountDO);
    }

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountDAO.selectById(id.getId());
        return TRANSLATOR.toAccount(accountDO);
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = TRANSLATOR.fromAccount(account);
        if (accountDO.getId() == null) {
            accountDAO.insert(accountDO);
        } else {
            accountDAO.update(accountDO);
        }
        return account;
    }
}

@Mapper
interface AccountBuilder {
    AccountBuilder TRANSLATOR = Mappers.getMapper(AccountBuilder.class);

    @Mappings({})
    Account toAccount(AccountDO accountDO);

    @Mappings({})
    AccountDO fromAccount(Account account);
}
