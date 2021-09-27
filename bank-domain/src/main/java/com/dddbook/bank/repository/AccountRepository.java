package com.dddbook.bank.repository;

import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.types.AccountId;
import com.dddbook.bank.types.AccountNumber;
import com.dddbook.bank.types.UserId;
import org.springframework.stereotype.Component;

/**
 * 数据存储层的业务抽象接口
 * 新建对象储存接口类AccountRepository：Repository只负责Entity对象的存储和读取，
 * 而Repository的实现类完成数据库存储的细节。通过加入Repository接口，底层的数据库连接可以通过不同的实现类而替换
 *
 * DAO 和 Repository 类的对比如下：
 *
 * DAO对应的是一个特定的数据库类型的操作，相当于SQL的封装。所有操作的对象都是DO类，所有接口都可以根据数据库实现的不同而改变。
 * 比如，insert 和 update 属于数据库专属的操作。
 *
 * Repository对应的是Entity对象读取储存的抽象，在接口层面做统一，不关注底层实现。比如，通过 save 保存一个Entity对象，
 * 但至于具体是 insert 还是 update 并不关心。Repository的具体实现类通过调用DAO来实现各种操作，
 * 通过Builder/Factory对象实现AccountDO 到 Account之间的转化
 *
 */
public interface AccountRepository {
    Account find(AccountId id);//通过账户id获取账户
    Account find(AccountNumber accountNumber);//通过账户号获取账户
    Account find(UserId userId);//通过用户id获取账户
    Account save(Account account);//保存账户信息
}
