package com.android.davidgj.cajeroautomaticose.codigochip.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.davidgj.cajeroautomaticose.codigochip.LoginChipPresenter;
import com.android.davidgj.cajeroautomaticose.codigochip.LoginChipPresenterImpl;
import com.android.davidgj.cajeroautomaticose.codigopin.ui.LoginPinActivity;
import com.android.davidgj.cajeroautomaticose.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginChipActivity extends AppCompatActivity implements LoginChipActivityView {

    @BindView(R.id.login_chip_et_codchip)
    EditText loginChipEtCodchip;
    @BindView(R.id.login_chip_btn_codchip)
    Button loginChipBtnCodchip;
    @BindView(R.id.loginchip_tv_loadingtext)
    TextView loginchipTvLoadingtext;

    public static  int codChipAux;

    public static final String CHIP_KEY = "key_key";

    LoginChipPresenter presenter;
    @BindView(R.id.content_chip)
    LinearLayout contentChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_chip);
        ButterKnife.bind(this);
        Snackbar.make(contentChip,"Ingrese una tarjeta porfavor", Snackbar.LENGTH_LONG);
        presenter = new LoginChipPresenterImpl(this);
        presenter.onCreate();

        loginchipTvLoadingtext.setVisibility(View.GONE);


    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showTextLoading() {
        setTextLoading(View.VISIBLE);
    }


    @Override
    public void hideTextLoading() {
        setTextLoading(View.GONE);
    }

    @Override
    public void enableView() {
        setView(true);
    }

    @Override
    public void disenableView() {
        setView(false);
    }


    @Override
    public void navigateToPinScreen() {
        Intent i = new Intent(LoginChipActivity.this, LoginPinActivity.class);
        i.putExtra(LoginChipActivity.CHIP_KEY, Integer.parseInt(loginChipEtCodchip.getText().toString().trim()));
        Log.e("CHIPTRAIDO1", Integer.parseInt(loginChipEtCodchip.getText().toString().trim())+"");
        startActivity(i);
        finish();
    }

    @OnClick(R.id.login_chip_btn_codchip)
    public void handleValidateChip() {
        String valorEditText = loginChipEtCodchip.getText().toString().trim();
        if (!valorEditText.isEmpty()) {

            presenter.validateCodChip(Integer.parseInt(loginChipEtCodchip.getText().toString().trim()));
        } else if (valorEditText.isEmpty()) {

            Snackbar.make(contentChip,"Ingrese una tarjeta porfavor", Snackbar.LENGTH_LONG).show();
    }
    }

    @Override
    public void setCodChipError(String error) {
        setTextLoading(View.VISIBLE);
        loginchipTvLoadingtext.setText(error);
    }

    //---------------
    private void setTextLoading(int visible) {
        loginchipTvLoadingtext.setVisibility(visible);
    }

    private void setView(boolean b) {
        loginChipEtCodchip.setEnabled(b);
        loginChipBtnCodchip.setEnabled(b);
    }


}
