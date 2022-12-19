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
import com.eric.sti3desafio.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<Item> items = new ArrayList<>();
    private Context context;

    public ItemAdapter(List<Item> items, Activity activity) {
        this.items = items;
        this.context = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemPedido = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);

        return new MyViewHolder(itemPedido);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item = items.get(position);

        holder.txvPreco.setText("R$ "+item.getValorUnitario());
        holder.txvQuantidade.setText(item.getQuantidade()+"x");
        holder.txvProduto.setText(item.getNome());

    }

    @Override
    public int getItemCount() {
        return (items == null) ? 0 : items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView txvProduto;
        private final TextView txvQuantidade;
        private final TextView txvPreco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txvProduto = itemView.findViewById(R.id.txvProduto);
            txvQuantidade = itemView.findViewById(R.id.txvQuantidade);
            txvPreco = itemView.findViewById(R.id.txvPreco);

        }

    }

}
