package com.android.davidgj.cajeroautomaticose.codigochip;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class LoginChipInteractorImpl implements  LoginChipInteractor{

    private LoginChipRepository repository;

    public LoginChipInteractorImpl() {

        this.repository = new LoginChipRepositoryImpl();
    }

    @Override
    public void doValidateCodChip(int codChip) {


        repository.doValidateCodChip(codChip);

    }
}
