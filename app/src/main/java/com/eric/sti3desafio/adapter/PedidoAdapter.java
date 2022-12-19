package com.eric.sti3desafio.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.model.Pedido;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder>{

    private List<Pedido> pedidos;
    private Context context;
    private RecyclerViewClickListener listener;

    public PedidoAdapter(List<Pedido> pedidoList, FragmentActivity activity, RecyclerViewClickListener listener){

        this.pedidos = pedidoList;
        this.context = activity;
        this.listener = listener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemPedido = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pedidos, parent, false);

        return new MyViewHolder(itemPedido);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pedido pedido = pedidos.get(position);

        holder.txvstatus.setText(pedido.getStatus());
        holder.txvnome.setText(pedido.getCliente().getNome());
        holder.txvemail.setText(pedido.getCliente().getEmail());
        holder.txvtotal.setText("R$ "+pedido.getValorTotal());
        holder.txvnumero.setText(Integer.toString(pedido.getNumero()));

        if ("APROVADO".equals(pedido.getStatus())) {
            holder.txvstatus.setTextColor(Color.parseColor("#247d1e"));
        } else {
            holder.txvstatus.setTextColor(Color.parseColor("#9c0e1a"));
        }

    }

    public Pedido getItem (int position) {
        return pedidos.get(position);
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView txvnome;
        private final TextView txvemail;
        private final TextView txvstatus;
        private final TextView txvtotal;
        private final TextView txvnumero;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txvnome = itemView.findViewById(R.id.txvnome);
            txvemail = itemView.findViewById(R.id.txvemail);
            txvstatus = itemView.findViewById(R.id.txvstatus);
            txvtotal = itemView.findViewById(R.id.txvtotal);
            txvnumero = itemView.findViewById(R.id.txvNumero);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    public void setFilteredList(List<Pedido> filteredList){
        this.pedidos = filteredList;
        notifyDataSetChanged();
    }

}
