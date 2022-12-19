package com.eric.sti3desafio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.activity.DetalhesActivity;
import com.eric.sti3desafio.adapter.PedidoAdapter;
import com.eric.sti3desafio.api.DataService;
import com.eric.sti3desafio.model.Pedido;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PesquisarFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerSearch;
    PedidoAdapter pedidoAdapter;
    private List<Pedido> pedidos = new ArrayList<>();
    private PedidoAdapter.RecyclerViewClickListener listener;
    Retrofit retrofit;

    public PesquisarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pesquisar, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = getView().findViewById(R.id.searchView);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://desafiotecnicosti3.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Recycler
        recyclerSearch = getView().findViewById(R.id.recyclerSearch);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerSearch.setLayoutManager(layoutManager);

        //define o adapter
        pedidoAdapter = new PedidoAdapter(pedidos, getActivity(), listener);
        recyclerSearch.setAdapter(pedidoAdapter);

        recyclerSearch.setHasFixedSize(true);

        retrofitData();
        pedidosClickListener();

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
                    recyclerSearch.setAdapter(pedidoAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {

            }

        });

    }

    public void pedidosClickListener(){

        listener = new PedidoAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), DetalhesActivity.class);

                startActivity(intent);

            }
        };

    }

    private void filterList(String text) {
        List<Pedido> filteredList = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().getNome().toLowerCase().contains(text.toLowerCase())) {

                filteredList.add(pedido);

            }
        }

        if (filteredList.isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(getView(), "Nenhum cliente encontrado", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            pedidoAdapter.setFilteredList(filteredList);
        }

    }

}