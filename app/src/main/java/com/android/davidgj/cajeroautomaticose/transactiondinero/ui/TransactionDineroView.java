package com.android.davidgj.cajeroautomaticose.transactiondinero.ui;

public interface TransactionDineroView  {

    void enableViewsIngresarMonto();
    void disableViewsIngresarMonto();

    void showMessage(String message);
    void hideMessage();

    void enableBtnSaldoAndSalir();
    void disableBtnSaldoAndSalir();

    void navigateToScreenSaldo();
    void navigateToSalir();

    void handleEnviarTransaction();

}
