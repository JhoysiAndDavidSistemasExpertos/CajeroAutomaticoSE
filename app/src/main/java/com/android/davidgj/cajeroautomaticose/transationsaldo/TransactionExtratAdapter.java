package com.android.davidgj.cajeroautomaticose.transationsaldo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.davidgj.cajeroautomaticose.R;
import com.android.davidgj.cajeroautomaticose.entities.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class TransactionExtratAdapter extends RecyclerView.Adapter<TransactionExtratAdapter.ViewHolder> {



    private List<Transaction> listTransaction;

    public TransactionExtratAdapter(List<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transation_saldo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = listTransaction.get(position);
        holder.tvTipo.setText(transaction.getTipo());
        holder.tvMonto.setText(transaction.getMonto()+"");
    }

    @Override
    public int getItemCount() {
        return listTransaction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tipo)
        TextView tvTipo;
        @BindView(R.id.tv_monto)
        TextView tvMonto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
