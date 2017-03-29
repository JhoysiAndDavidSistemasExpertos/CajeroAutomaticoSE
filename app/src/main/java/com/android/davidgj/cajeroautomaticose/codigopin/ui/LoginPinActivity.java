package com.android.davidgj.cajeroautomaticose.codigopin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.davidgj.cajeroautomaticose.R;
import com.android.davidgj.cajeroautomaticose.codigochip.ui.LoginChipActivity;
import com.android.davidgj.cajeroautomaticose.codigopin.LoginPinPresenter;
import com.android.davidgj.cajeroautomaticose.codigopin.LoginPinPresenterImp;
import com.android.davidgj.cajeroautomaticose.usermenu.ui.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginPinActivity extends AppCompatActivity implements LoginPinActivityView {

    @BindView(R.id.login_pin_et_codpin)
    EditText loginPinEtCodpin;
    @BindView(R.id.login_pin_btn_codpin)
    Button loginPinBtnCodpin;
    @BindView(R.id.loginpin_tv_loadingtext)
    TextView loginpinTvLoadingtext;

    //public int pruebaCOdChip = 111;
    Intent traerhip;
    private LoginPinPresenter loginPinPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pin);
        ButterKnife.bind(this);

        loginPinPresenter = new LoginPinPresenterImp(this);
        loginPinPresenter.onCreate();
        setTextLoading(View.GONE);
         traerhip = getIntent();

    }

    @Override
    protected void onDestroy() {
        loginPinPresenter.onDestroy();
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
        setViews(true);
    }

    @Override
    public void disenableView() {
        setViews(false);
    }

    @Override
    public void navigateToPinScreen() {
        Intent i = new Intent(this, MenuActivity.class);
         traerhip = getIntent();
        i.putExtra(LoginChipActivity.CHIP_KEY, traerhip.getIntExtra(LoginChipActivity.CHIP_KEY,000));
        startActivity(i);
        finish();
    }
    @OnClick(R.id.login_pin_btn_codpin)
    @Override
    public void handleCodPinAndCodChip() {
        String codPinStr = loginPinEtCodpin.getText().toString().trim();
        if (!codPinStr.isEmpty()) {
            loginPinPresenter.validateCodPinAndCodChip(Integer.parseInt(codPinStr)
                    , traerhip.getIntExtra(LoginChipActivity.CHIP_KEY, 000));
        }else if(codPinStr.isEmpty()){
            Toast.makeText(this, "Ingrese Pin porfavor", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setcodPinError(String error) {
        setTextLoading(View.VISIBLE);
        loginpinTvLoadingtext.setText(error);
        loginPinEtCodpin.setText("");
    }

    private void setTextLoading(int visible) {
        loginpinTvLoadingtext.setVisibility(visible);
    }
    private void setViews(boolean enable) {
        loginPinBtnCodpin.setEnabled(enable);
        loginPinEtCodpin.setEnabled(enable);
    }
}
