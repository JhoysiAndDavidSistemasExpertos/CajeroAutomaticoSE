package com.android.davidgj.cajeroautomaticose.usermenu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


import com.android.davidgj.cajeroautomaticose.R;
import com.android.davidgj.cajeroautomaticose.codigochip.ui.LoginChipActivity;
import com.android.davidgj.cajeroautomaticose.entities.Comunicador;
import com.android.davidgj.cajeroautomaticose.entities.Transaction;
import com.android.davidgj.cajeroautomaticose.entities.User;
import com.android.davidgj.cajeroautomaticose.transactiondinero.ui.TransactionDineroActivity;
import com.android.davidgj.cajeroautomaticose.transationsaldo.TransactionSaldoActivity;
import com.android.davidgj.cajeroautomaticose.usermenu.MenuPresenter;
import com.android.davidgj.cajeroautomaticose.usermenu.MenuPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity implements MenuActivityView{

    @BindView(R.id.btn_menu_retirar_dinero)
    Button btnMenuRetirarDinero;
    @BindView(R.id.btn_menu_depositar)
    Button btnMenuDepositar;
    @BindView(R.id.btn_menu_saldo)
    Button btnMenuSaldo;
    @BindView(R.id.btn_menu_extracto)
    Button btnMenuExtracto;
    private MenuPresenter presenter;
    Intent intent ;


    public static final String KEY_TRANSACTION = "key_take_money";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        intent = getIntent();
        presenter = new MenuPresenterImpl(this);
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    @OnClick(R.id.btn_menu_retirar_dinero)
    @Override
    public void handleRetirarDinero() {
        Intent enviarChip = new Intent(this, TransactionDineroActivity.class);
        enviarChip.putExtra(LoginChipActivity.CHIP_KEY,intent.getIntExtra(LoginChipActivity.CHIP_KEY,000));
        enviarChip.putExtra(KEY_TRANSACTION, 0);
        startActivity(enviarChip);
    }

    @OnClick(R.id.btn_menu_depositar)
    @Override
    public void handleDepositarDinero() {
        Intent enviarChip = new Intent(this, TransactionDineroActivity.class);
        enviarChip.putExtra(LoginChipActivity.CHIP_KEY,intent.getIntExtra(LoginChipActivity.CHIP_KEY,000));
        enviarChip.putExtra(KEY_TRANSACTION, 1);
        startActivity(enviarChip);    }

    @OnClick(R.id.btn_menu_saldo)
    @Override
    public void handleVerSaldo() {
        Log.e("Click"," saldo");
        Intent i = getIntent();
        int codChip = i.getIntExtra(LoginChipActivity.CHIP_KEY,000);
        presenter.seeBalance(codChip);
    }

    @OnClick(R.id.btn_menu_extracto)
    @Override
    public void handleVerExtracto() {
        Intent i = getIntent();
        int codChip = i.getIntExtra(LoginChipActivity.CHIP_KEY,000);
        presenter.seeExtract(codChip);
    }

    @Override
    public void showBalance(User userSaldo) {
        //mandar a ontra actividad y en un intent el saldo para q muestre
        tipoMetodo(userSaldo,1,new ArrayList<Transaction>());
    }

    @Override
    public void showExtract(User extractUser, ArrayList<Transaction> arrayList) {
//mandar a ontra actividad y en un intent la lista de extracto para q muestre
//        Log.e("Click showExtract", arrayList.get(0).getDate());
        tipoMetodo(extractUser,2, arrayList);

    }

    public void tipoMetodo(User user, int tipo, ArrayList<Transaction> transactionList) {
        Intent i = new Intent(this, TransactionSaldoActivity.class);

        i.putExtra("tipoMetodo", tipo);
        i.putExtra("name", user.getName());
        i.putExtra("saldo", user.getSaldo());

        if (tipo == 2) {


            Comunicador.setTransactions(transactionList);

            startActivity(i);
        }
    }

    @Override
    public void setError(String errorMessage) {
        //implementar en el menu en un text view
    }

}
