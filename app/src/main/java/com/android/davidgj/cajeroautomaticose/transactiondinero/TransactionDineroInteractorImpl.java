package com.android.davidgj.cajeroautomaticose.transactiondinero;

/**
 * Created by DaviDGJ on 29/3/2017.
 */

public  class TransactionDineroInteractorImpl implements TransactionDineroInteractor{

    private TransactionDineroRepository repository;

    public TransactionDineroInteractorImpl() {
        this.repository = new TransactionDineroRepositoryImpl();
    }

    @Override
    public void takeMoney(int codChip, double monto) {
        repository.takeMoney(codChip, monto);
    }

    @Override
    public void depositMoney(int codChip, double monto) {
repository.depositMoney(codChip, monto);
    }
}
