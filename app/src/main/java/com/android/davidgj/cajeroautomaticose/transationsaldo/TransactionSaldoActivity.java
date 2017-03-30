package com.android.davidgj.cajeroautomaticose.transationsaldo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.android.davidgj.cajeroautomaticose.R;
import com.android.davidgj.cajeroautomaticose.codigochip.ui.LoginChipActivity;
import com.android.davidgj.cajeroautomaticose.entities.Comunicador;
import com.android.davidgj.cajeroautomaticose.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionSaldoActivity extends AppCompatActivity {

    @BindView(R.id.tv_transaction_saldo)
    TextView tvTransactionSaldo;
    @BindView(R.id.rv_transaction_saldo)
    RecyclerView rvTransactionSaldo;
    @BindView(R.id.btn_saldo_aceptar)
    Button btnSaldoAceptar;

    private TransactionExtratAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion_saldo);
        ButterKnife.bind(this);

        Intent tipo = getIntent();
        int tipoMetodo = tipo.getIntExtra("tipoMetodo", 000);


        switch (tipoMetodo) {
            case 1:
                //saldo

                tvTransactionSaldo.setText("Usuario:  " + tipo.getStringExtra("name") + " Saldo: " + tipo.getDoubleExtra("saldo", 0.00));
                break;

            case 2:
                //extracto
                tvTransactionSaldo.setText("Usuario:  " + tipo.getStringExtra("name") + " Saldo: " + tipo.getDoubleExtra("saldo", 0.00));
                Comunicador.getTransactions();
                setupAdapter(Comunicador.getTransactions());
                setupRecclerView();

                break;
            default:
                break;
        }
        //tvTransactionSaldo.setText(tipo.getIntExtra("llavesaldo",000));

    }

    private void setupAdapter(ArrayList<Transaction> listTransaction) {
        adapter = new TransactionExtratAdapter(listTransaction);
    }


    private void setupRecclerView() {
        rvTransactionSaldo.setLayoutManager(new LinearLayoutManager(this));
        rvTransactionSaldo.setAdapter(adapter);
    }

    @OnClick(R.id.btn_saldo_aceptar)
    public void handleAceptar(){
        startActivity(new Intent(this, LoginChipActivity.class));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginChipActivity.class);
        startActivity(intent);
    }

}
