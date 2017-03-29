package com.android.davidgj.cajeroautomaticose.transactiondinero;

/**
 * Created by DaviDGJ on 29/3/2017.
 */

public interface TransactionDineroRepository {

    void takeMoney(int codChip, double monto);
    void depositMoney(int codChip, double monto);
}
