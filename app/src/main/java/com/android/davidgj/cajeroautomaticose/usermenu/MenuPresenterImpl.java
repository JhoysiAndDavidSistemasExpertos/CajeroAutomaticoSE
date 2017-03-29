package com.android.davidgj.cajeroautomaticose.usermenu;



import com.android.davidgj.cajeroautomaticose.entities.User;
import com.android.davidgj.cajeroautomaticose.lib.EventBusHelper;
import com.android.davidgj.cajeroautomaticose.lib.GreenRobotEventBusHelperImple;
import com.android.davidgj.cajeroautomaticose.usermenu.events.MenuEvent;
import com.android.davidgj.cajeroautomaticose.usermenu.ui.MenuActivityView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class MenuPresenterImpl implements MenuPresenter{

    private MenuActivityView view;
    private MenuInteractor interactor;
    private EventBusHelper eventBusHelper;

    public MenuPresenterImpl(MenuActivityView view) {
        this.view = view;
        this.interactor = new MenuInteractorImpl();
        this.eventBusHelper = GreenRobotEventBusHelperImple.getInstance();
    }

    @Override
    public void onDestroy() {
        view= null;
        eventBusHelper.unregister(this);
    }

    @Override
    public void onCreate() {
        eventBusHelper.register(this);
    }

    @Override
    public void seeBalance(int codChip) {
        interactor.seeBalance(codChip);
    }

    @Override
    public void seeExtract(int codChip) {
        interactor.seeExtract(codChip);
    }

    @Subscribe
    @Override
    public void onEventMainThread(MenuEvent event) {
        switch (event.getTypeEvent()){
            case MenuEvent.verSaldo:
                verSaldo(event.getUser());
                break;
            case MenuEvent.verExxtracto:
                verExxtracto(event.getUser());
                break;
            case MenuEvent.errorTrasation:
                errorTrasation(event.getErrorMessage());
                break;

        }

    }

    private void errorTrasation(String errorMessage) {
        view.setError(errorMessage);
    }

    private void verExxtracto(User user) {
        view.showExtract(user);
    }

    private void verSaldo(User saldoCuenta) {

    view.showBalance(saldoCuenta);

    }
}
