package com.android.davidgj.cajeroautomaticose.transactiondinero;

import com.android.davidgj.cajeroautomaticose.transactiondinero.events.TransactinDineroEvent;

public interface TransactionDineroPresenter{

    void onDestroy();
    void onCreate();


    void takeMoney(int codChip, double monto);
    void depositMoney(int codChip, double monto);

    void onEventMainThread(TransactinDineroEvent event);


}
