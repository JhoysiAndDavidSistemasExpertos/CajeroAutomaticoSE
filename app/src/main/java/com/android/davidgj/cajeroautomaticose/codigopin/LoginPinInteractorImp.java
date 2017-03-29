package com.android.davidgj.cajeroautomaticose.codigopin;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class LoginPinInteractorImp implements LoginPinInteractor{

    LoginPinRepository repository;

    public LoginPinInteractorImp() {
        this.repository = new LoginPinRepositoryImp();
    }

    @Override
    public void doValidateCodPinAndCodChip(int codPin, int codChip) {
            repository.doValidateCodPinAndCodChip(codPin, codChip);
    }
}
