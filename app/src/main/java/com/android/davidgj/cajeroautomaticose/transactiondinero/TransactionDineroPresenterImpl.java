package com.android.davidgj.cajeroautomaticose.transactiondinero;

import com.android.davidgj.cajeroautomaticose.entities.Comunicador;
import com.android.davidgj.cajeroautomaticose.entities.User;
import com.android.davidgj.cajeroautomaticose.lib.EventBusHelper;
import com.android.davidgj.cajeroautomaticose.lib.GreenRobotEventBusHelperImple;
import com.android.davidgj.cajeroautomaticose.transactiondinero.events.TransactinDineroEvent;
import com.android.davidgj.cajeroautomaticose.transactiondinero.ui.TransactionDineroActivity;

import org.greenrobot.eventbus.Subscribe;

public class TransactionDineroPresenterImpl implements TransactionDineroPresenter{

    private EventBusHelper eventBusHelper;
    private TransactionDineroActivity view;
    private TransactionDineroInteractor interactor;

    public TransactionDineroPresenterImpl(TransactionDineroActivity view) {
        this.view = view;
        this.eventBusHelper = GreenRobotEventBusHelperImple.getInstance();
        this.interactor = new TransactionDineroInteractorImpl();
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBusHelper.unregister(this);
    }

    @Override
    public void onCreate() {
        eventBusHelper.register(this);
    }

    @Override
    public void takeMoney(int codChip, double monto) {
        interactor.takeMoney(codChip,monto);
    }

    @Override
    public void depositMoney(int codChip, double monto) {
        interactor.depositMoney(codChip, monto);
    }

    @Subscribe
    @Override
    public void onEventMainThread(TransactinDineroEvent event) {

        //posteamos eventossegun sea el caso

        switch (event.getEventType()){
            case TransactinDineroEvent.TRANSACTION_SUCCESS:
                transactionSucces(event.getUser());
                break;
            case TransactinDineroEvent.TRANSACTION_ERROR:
                transactionError(event.getErrorMessage());
                break;

        }
    }

    private void transactionError(String errorMessage) {

        view.showMessage(errorMessage);
        view.disableViewsIngresarMonto();
        view.enableBtnSaldoAndSalir();
    }

    private void transactionSucces(User user) {
        view.showMessage("Transacion Exitosa");
        view.disableViewsIngresarMonto();
        view.enableBtnSaldoAndSalir();
        Comunicador.setObjeto(user);
    }
}
