package com.android.davidgj.cajeroautomaticose.codigopin;


import com.android.davidgj.cajeroautomaticose.codigopin.events.LoginPinEvent;
import com.android.davidgj.cajeroautomaticose.codigopin.ui.LoginPinActivityView;
import com.android.davidgj.cajeroautomaticose.lib.EventBusHelper;
import com.android.davidgj.cajeroautomaticose.lib.GreenRobotEventBusHelperImple;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class LoginPinPresenterImp implements LoginPinPresenter {

    private LoginPinActivityView view;
    private EventBusHelper eventBusHelper;
    private LoginPinInteractor interactor;

    public LoginPinPresenterImp(LoginPinActivityView view) {
        this.view = view;
        this.eventBusHelper = GreenRobotEventBusHelperImple.getInstance();
        this.interactor = new LoginPinInteractorImp();
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
    public void validateCodPinAndCodChip(int codPin, int codChip) {
        if (view!=null){
            view.disenableView();
            view.showTextLoading();
        }
        interactor.doValidateCodPinAndCodChip(codPin, codChip);
    }

    @Subscribe
    @Override
    public void onEventMainThread(LoginPinEvent event) {
        switch (event.getEventType()){
            case LoginPinEvent.PIN_LOGIN_SUCCESS:
                    onPinSuccess();
                break;
            case LoginPinEvent.PIN_LOGIN_ERROR:
                    onPinError(event.getMessageError());
                break;
        }
    }

    private void onPinSuccess(){
        if (view != null){
            view.navigateToPinScreen();
        }
    }
    private void onPinError(String error){
        if (view != null){
            view.setcodPinError(error);
            view.enableView();

        }
    }

}
