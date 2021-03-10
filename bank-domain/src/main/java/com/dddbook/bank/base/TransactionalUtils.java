package com.dddbook.bank.base;

import lombok.SneakyThrows;
import org.springframework.transaction.support.TransactionSynchronization;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isSynchronizationActive;
import static org.springframework.transaction.support.TransactionSynchronizationManager.registerSynchronization;

public class TransactionalUtils {
    private static final AtomicInteger threadIndex = new AtomicInteger(0);

    private static final ExecutorService executorService = newFixedThreadPool(10, r -> {
        Thread t = new Thread(r, "notify-" + threadIndex.getAndIncrement());
        t.setDaemon(true);
        return t;
    });

    @FunctionalInterface
    public interface EventCallback {
        void apply();
    }

    public static void applyAfterCommit(@NotNull EventCallback eventCallback) {
        if (!isSynchronizationActive()) {
            eventCallback.apply();
            return;
        }
        registerSynchronization(new TransactionSynchronization() {
            @SneakyThrows
            @Override
            public void afterCommit() {
                runAsync(eventCallback::apply, executorService).get();
            }
        });
    }

}
