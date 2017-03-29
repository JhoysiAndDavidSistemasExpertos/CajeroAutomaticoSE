package com.android.davidgj.cajeroautomaticose.usermenu;

import android.util.Log;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class MenuInteractorImpl implements MenuInteractor{

    private MenuRepository repository;

    public MenuInteractorImpl() {
        this.repository = new MenuRepositoryImpl();
    }


    @Override
    public void seeBalance(int codChip) {
        Log.e("seeBalance1", "estoy aqui");
repository.seeBalance(codChip);
    }

    @Override
    public void seeExtract(int codChip) {
    repository.seeExtract(codChip);
    }
}
