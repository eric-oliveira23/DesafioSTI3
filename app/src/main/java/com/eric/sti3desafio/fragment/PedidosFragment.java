package com.eric.sti3desafio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.activity.DetalhesActivity;
import com.eric.sti3desafio.adapter.PedidoAdapter;
import com.eric.sti3desafio.api.DataService;
import com.eric.sti3desafio.model.Pedido;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidosFragment extends Fragment {

    private RecyclerView recyclerPedidos;
    private List<Pedido> pedidos = new ArrayList<>();
    private PedidoAdapter pedidoAdapter;
    Retrofit retrofit;
    private PedidoAdapter.RecyclerViewClickListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://desafiotecnicosti3.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerPedidos = getView().findViewById(R.id.recyclerPedidos);

        pedidosClickListener();
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerPedidos.setLayoutManager(layoutManager);

        recyclerPedidos.setHasFixedSize(true);

        retrofitData();

    }

    private void retrofitData(){

        DataService dataService = retrofit.create(DataService.class);
        Call<List<Pedido>> pedidoCall = dataService.recuperarPedidos();

        pedidoCall.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                if (response.isSuccessful()){
                    pedidos = response.body();
                    //define o adapter
                    pedidoAdapter = new PedidoAdapter(pedidos, getActivity(), listener);
                    recyclerPedidos.setAdapter(pedidoAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.i("info", t+"");
            }

        });

    }

    public void pedidosClickListener(){

        listener = new PedidoAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), DetalhesActivity.class);

                //endereço
                intent.putExtra("rua", pedidoAdapter.getItem(position).getEnderecoEntrega().getEndereco());
                intent.putExtra("numero", pedidoAdapter.getItem(position).getEnderecoEntrega().getNumero());
                intent.putExtra("cep", pedidoAdapter.getItem(position).getEnderecoEntrega().getCep());
                intent.putExtra("bairro", pedidoAdapter.getItem(position).getEnderecoEntrega().getBairro());
                intent.putExtra("cidade", pedidoAdapter.getItem(position).getEnderecoEntrega().getCidade());
                intent.putExtra("estado", pedidoAdapter.getItem(position).getEnderecoEntrega().getEndereco());
                intent.putExtra("complemento", pedidoAdapter.getItem(position).getEnderecoEntrega().getComplemento());
                intent.putExtra("referencia", pedidoAdapter.getItem(position).getEnderecoEntrega().getReferencia());

                //cliente
                intent.putExtra("nome", pedidoAdapter.getItem(position).getCliente().getNome());

                if (pedidoAdapter.getItem(position).getCliente().getCpf().equals("")){
                    intent.putExtra("documento", pedidoAdapter.getItem(position).getCliente().getCnpj());
                }
                else{
                    intent.putExtra("documento", pedidoAdapter.getItem(position).getCliente().getCpf());
                }

                intent.putExtra("nascimento", pedidoAdapter.getItem(position).getCliente().getDataNascimento());
                intent.putExtra("email", pedidoAdapter.getItem(position).getCliente().getEmail());

                //pedido
                intent.putExtra("desconto", pedidoAdapter.getItem(position).getDesconto());
                intent.putExtra("frete", pedidoAdapter.getItem(position).getFrete());
                intent.putExtra("subtotal", pedidoAdapter.getItem(position).getSubTotal());
                intent.putExtra("valortotal", pedidoAdapter.getItem(position).getValorTotal());
                intent.putExtra("numeroPedido", Integer.toString(pedidoAdapter.getItem(position).getNumero()));

                intent.putParcelableArrayListExtra("data", new ArrayList<>(pedidoAdapter.getItem(position).getItens()));

                startActivity(intent);

            }
        };

    }

}