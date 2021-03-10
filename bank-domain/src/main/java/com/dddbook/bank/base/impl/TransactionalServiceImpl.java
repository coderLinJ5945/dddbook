package com.dddbook.bank.base.impl;

import com.dddbook.bank.base.Action;
import com.dddbook.bank.base.TransactionalService;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

public class TransactionalServiceImpl implements TransactionalService {

    @Override
    @Transactional
    public void transaction(Action action) {
        action.apply();
    }

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public void newTransaction(Action action) {
        action.apply();
    }

    @Override
    @Transactional(propagation = NOT_SUPPORTED)
    public void notTransaction(Action action) {
        action.apply();
    }
}
