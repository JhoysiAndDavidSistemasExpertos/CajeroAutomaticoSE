package com.android.davidgj.cajeroautomaticose.transactiondinero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.android.davidgj.cajeroautomaticose.R;
import com.android.davidgj.cajeroautomaticose.codigochip.ui.LoginChipActivity;
import com.android.davidgj.cajeroautomaticose.usermenu.ui.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionDineroActivity extends AppCompatActivity implements TransactionDineroView{

    @BindView(R.id.et_dinero_monto)
    EditText etDineroMonto;
    @BindView(R.id.btn_dinero_enviar)
    Button btnDineroEnviar;
    @BindView(R.id.tv_dinero_message)
    TextView tvDineroMessage;
    @BindView(R.id.btn_dinero_versaldo)
    Button btnDineroVersaldo;
    @BindView(R.id.btn_dinero_salir)
    Button btnDineroSalir;

    private TransactionDineroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);
    }
//--------------------------------------------------------
    @Override
    public void enableViewIngresarMonto() {

    }

    @Override
    public void disableViewIngresarMonto() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideMessage() {

    }

    @Override
    public void enableBtnSaldoAndSalir() {

    }

    @Override
    public void disableBtnSaldoAndSalir() {

    }

    @Override
    public void navigateToScreen() {

    }

    @Override
    public void navigateToSalir() {

    }

    @Override
    public void handleEnviarTransaction() {
        Intent i = getIntent();
        int codTransacion = i.getIntExtra(MenuActivity.KEY_TRANSACTION,000);
        int codChip = i.getIntExtra(LoginChipActivity.CHIP_KEY,000);
        switch (codTransacion){
            case 0:
                //retirar dinero mandando el codigo del chip
                break;
            case 1:
                //depositar dinero mandando el codigo del chip
                break;
            default:
                break;
        }
    }
    //--------------------------------------------------------
}
