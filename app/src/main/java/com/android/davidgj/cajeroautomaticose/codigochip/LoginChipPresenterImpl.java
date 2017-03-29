package com.android.davidgj.cajeroautomaticose.codigochip;



import com.android.davidgj.cajeroautomaticose.codigochip.events.LoginChipEvent;
import com.android.davidgj.cajeroautomaticose.codigochip.ui.LoginChipActivity;
import com.android.davidgj.cajeroautomaticose.lib.EventBusHelper;
import com.android.davidgj.cajeroautomaticose.lib.GreenRobotEventBusHelperImple;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class LoginChipPresenterImpl implements LoginChipPresenter {

    private LoginChipActivity view;
    private LoginChipInteractor interactor;
    private EventBusHelper eventbus;


    public LoginChipPresenterImpl(LoginChipActivity view) {
        this.view = view;
        this.interactor = new LoginChipInteractorImpl();
        this.eventbus = GreenRobotEventBusHelperImple.getInstance();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventbus.unregister(this);
    }

    @Override
    public void onCreate() {
        eventbus.register(this);
    }

    @Override
    public void validateCodChip(int codChip) {

        if(view!=null){
            view.disenableView();
            view.showTextLoading();
        }
        interactor.doValidateCodChip(codChip);
    }

    @Subscribe
    @Override
    public void onEventMainThred(LoginChipEvent event) {
    switch (event.getEventType()){
        case LoginChipEvent.onCodChipError:
            onCodChipError(event.getErrorMessage());
            break;
        case LoginChipEvent.onCodChipSuccess:
            onCodChipSuccess();
            break;
    }
    }

    private void onCodChipSuccess() {
        if (view!=null){
            view.navigateToPinScreen();
        }

    }

    private void onCodChipError(String error) {
        if(view!=null){
            view.enableView();
            view.hideTextLoading();
            view.setCodChipError(error);
        }
    }
}
