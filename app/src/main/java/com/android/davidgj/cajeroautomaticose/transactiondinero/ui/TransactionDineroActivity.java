package com.android.davidgj.cajeroautomaticose.transactiondinero.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.davidgj.cajeroautomaticose.R;
import com.android.davidgj.cajeroautomaticose.codigochip.ui.LoginChipActivity;
import com.android.davidgj.cajeroautomaticose.entities.Comunicador;
import com.android.davidgj.cajeroautomaticose.entities.User;
import com.android.davidgj.cajeroautomaticose.transactiondinero.TransactionDineroPresenter;
import com.android.davidgj.cajeroautomaticose.transactiondinero.TransactionDineroPresenterImpl;
import com.android.davidgj.cajeroautomaticose.transationsaldo.TransactionSaldoActivity;
import com.android.davidgj.cajeroautomaticose.usermenu.ui.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionDineroActivity extends AppCompatActivity implements TransactionDineroView {

    @BindView(R.id.et_dinero_monto)
    EditText etDineroMonto;
    @BindView(R.id.btn_dinero_enviar)
    Button btnDineroEnviar;
    @BindView(R.id.tv_dinero_message_name)
    TextView tvDineroMessageName;
    @BindView(R.id.tv_dinero_message_saldo)
    TextView tvDineroMessageSaldo;
    @BindView(R.id.btn_dinero_versaldo)
    Button btnDineroVersaldo;
    @BindView(R.id.btn_dinero_salir)
    Button btnDineroSalir;
    @BindView(R.id.view_trnasction_dinero)
    LinearLayout viewTrnasctionDinero;

    private TransactionDineroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_dinero);
        ButterKnife.bind(this);
        btnDineroVersaldo.setEnabled(false);
        btnDineroSalir.setEnabled(false);
        btnDineroVersaldo.setVisibility(View.INVISIBLE);
        btnDineroSalir.setVisibility(View.INVISIBLE);
        tvDineroMessageName.setVisibility(View.GONE);
        tvDineroMessageSaldo.setVisibility(View.GONE);
        this.presenter = new TransactionDineroPresenterImpl(this);
        presenter.onCreate();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //--------------------------------------------------------
    @Override
    public void enableViewsIngresarMonto() {
        etDineroMonto.setEnabled(true);
        btnDineroEnviar.setEnabled(true);
    }

    @Override
    public void disableViewsIngresarMonto() {
        etDineroMonto.setEnabled(false);
        btnDineroEnviar.setEnabled(false);
    }

    @Override
    public void showMessage(String message) {
        tvDineroMessageName.setVisibility(View.VISIBLE);
        tvDineroMessageName.setText(message);
    }

    @Override
    public void hideMessage() {

        tvDineroMessageName.setVisibility(View.GONE);
        tvDineroMessageSaldo.setVisibility(View.GONE);
    }

    @Override
    public void enableBtnSaldoAndSalir() {
        btnDineroVersaldo.setEnabled(true);
        btnDineroSalir.setEnabled(true);
        btnDineroVersaldo.setVisibility(View.VISIBLE);
        btnDineroSalir.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableBtnSaldoAndSalir() {
        btnDineroVersaldo.setEnabled(false);
        btnDineroSalir.setEnabled(false);
        btnDineroVersaldo.setVisibility(View.INVISIBLE);
        btnDineroSalir.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btn_dinero_versaldo)
    @Override
    public void navigateToScreenSaldo() {

        //FALTA VER SI MUESTRA EL SALDO O TENDREMOS Q MANDARLE UN CODIGO DEL CHIP PARA Q MUESTRE
        User user = (User) Comunicador.getObjeto();
        tvDineroMessageName.setText("Usuario: " + user.getName());
        tvDineroMessageSaldo.setVisibility(View.VISIBLE);
        tvDineroMessageSaldo.setText("Saldo: "+user.getSaldo());
    }
    @OnClick(R.id.btn_dinero_salir)
    @Override
    public void navigateToSalir() {
        startActivity(new Intent(this, LoginChipActivity.class));
    }

    @OnClick(R.id.btn_dinero_enviar)
    @Override
    public void handleEnviarTransaction() {
        Intent i = getIntent();
        int codTransacion = i.getIntExtra(MenuActivity.KEY_TRANSACTION, 000);
        int codChip = i.getIntExtra(LoginChipActivity.CHIP_KEY, 000);

        //si el edit text no esta vacio
        if (!etDineroMonto.getText().toString().isEmpty()) {

            double monto = Double.parseDouble(etDineroMonto.getText().toString().trim());

            switch (codTransacion) {
                case 0:
                    //retirar dinero mandando el codigo del chip y el monto a retiar
                    //comentario prueba para subir a git
                    presenter.takeMoney(codChip,monto);

                    break;
                case 1:
                    //depositar dinero mandando el codigo del chip  y el monto a depositar
                    presenter.depositMoney(codChip,monto);
                    break;
                default:
                    break;
            }

        } else {
            Snackbar.make(viewTrnasctionDinero,"Ingrese un monto porfavor",Snackbar.LENGTH_SHORT).show();
        }

    }
    //--------------------------------------------------------



    @Override
    public void onBackPressed() {
       Intent intent = new Intent(this, LoginChipActivity.class);
        startActivity(intent);
    }
}
