package com.android.davidgj.cajeroautomaticose.transationsaldo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.android.davidgj.cajeroautomaticose.R;
import com.android.davidgj.cajeroautomaticose.entities.Comunicador;
import com.android.davidgj.cajeroautomaticose.entities.Transaction;
import com.android.davidgj.cajeroautomaticose.entities.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionSaldo extends AppCompatActivity {

    @BindView(R.id.tv_transaction_saldo)
    TextView tvTransactionSaldo;
    @BindView(R.id.rv_transaction_saldo)
    RecyclerView rvTransactionSaldo;

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
                User userSaldo = (User) Comunicador.getObjeto();
                tvTransactionSaldo.setText("hola " + userSaldo.getName() + " tu saldo es: " + userSaldo.getSaldo());
                break;

            case 2:
                //extracto
                User userExtracto = (User) Comunicador.getObjeto();



                break;
            default:
                break;
        }

    }

    private void setupAdapter(List<Transaction> listTransaction) {
        adapter = new TransactionExtratAdapter(listTransaction);
    }


    private void setupRecclerView() {
        rvTransactionSaldo.setLayoutManager(new LinearLayoutManager(this));
        rvTransactionSaldo.setAdapter(adapter);
    }

}
