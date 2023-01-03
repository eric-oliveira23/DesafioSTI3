package com.eric.sti3desafio.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.model.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class PagamentoAdapter extends RecyclerView.Adapter<PagamentoAdapter.MyViewHolder> {

    private List<Pagamento> pagamentos = new ArrayList<>();
    private Context context;

    public PagamentoAdapter(List<Pagamento> pagamentos, Activity activity) {
        this.pagamentos = pagamentos;
        this.context = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemPagamento = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payments, parent, false);

        return new MyViewHolder(itemPagamento);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pagamento pagamento = pagamentos.get(position);

        holder.txvPortion.setText("Parcela "+pagamento.getParcela());
        holder.txvPaymentMode.setText(pagamento.getNome());
        holder.txvPortionValue.setText("R$ "+pagamento.getValor());

    }

    @Override
    public int getItemCount() {
        return (pagamentos == null) ? 0 : pagamentos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView txvPortion;
        private final TextView txvPaymentMode;
        private final TextView txvPortionValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txvPortion = itemView.findViewById(R.id.txvPortion);
            txvPaymentMode = itemView.findViewById(R.id.txvPaymentMode);
            txvPortionValue = itemView.findViewById(R.id.txvPortionValue);

        }
    }
}
