package com.dddbook.bank.base.config;

import com.dddbook.bank.base.TransactionalService;
import com.dddbook.bank.base.impl.TransactionalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionalServiceAutoConfig {
    @Bean
    public TransactionalService transactionalService() {
        return new TransactionalServiceImpl();
    }

//    @Bean public PlatformTransactionManager txManager() { return new DataSourceTransactionManager(dataSource()); }
}
