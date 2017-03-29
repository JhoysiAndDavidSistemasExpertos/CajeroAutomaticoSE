package com.android.davidgj.cajeroautomaticose.transactiondinero;

public interface TransactionDineroView  {

    void enableViewIngresarMonto();
    void disableViewIngresarMonto();

    void showMessage(String message);
    void hideMessage();

    void enableBtnSaldoAndSalir();
    void disableBtnSaldoAndSalir();

    void navigateToScreen();
    void navigateToSalir();

    void handleEnviarTransaction();

}
