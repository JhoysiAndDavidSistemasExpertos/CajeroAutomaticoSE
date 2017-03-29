package com.android.davidgj.cajeroautomaticose.codigopin;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public interface LoginPinRepository {
    void doValidateCodPinAndCodChip(int codPin, int codChip);
}
