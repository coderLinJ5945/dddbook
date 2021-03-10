package com.dddbook.bank.base;

public interface TransactionalService {
    void transaction(Action action);

    void newTransaction(Action action);

    void notTransaction(Action action);
}
