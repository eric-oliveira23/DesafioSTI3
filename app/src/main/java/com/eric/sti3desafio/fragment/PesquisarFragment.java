package com.eric.sti3desafio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.activity.DetalhesActivity;
import com.eric.sti3desafio.adapter.PedidoAdapter;
import com.eric.sti3desafio.api.DataService;
import com.eric.sti3desafio.database.MyDatabase;
import com.eric.sti3desafio.model.Pedido;
import com.facebook.shimmer.ShimmerFrameLayout;
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
    List<Pedido> pedidosDb = new ArrayList<>();
    private PedidoAdapter.RecyclerViewClickListener listener;
    Retrofit retrofit;
    private MyDatabase db;
    private ShimmerFrameLayout shimmerFrameLayout;

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

        shimmerFrameLayout = getView().findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();

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

        db = Room.databaseBuilder(getActivity(), MyDatabase.class, "MyDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        pedidoCall.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                if (response.isSuccessful()) {

                    pedidos = response.body();
                    db.pedidoDAO().insertAll(pedidos);
                }
                setAdapter();
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.i("info", t + "");
                setAdapter();
            }
        });
    }

    public void pedidosClickListener() {

        listener = new PedidoAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), DetalhesActivity.class);

                intent.putExtra("pedidoData", pedidoAdapter.getItem(position));

                startActivity(intent);

            }
        };

    }

    public void setAdapter() {
        pedidosDb = db.pedidoDAO().getAll();

        pedidoAdapter = new PedidoAdapter(pedidosDb, getActivity(), listener);
        recyclerSearch.setAdapter(pedidoAdapter);

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

    private void filterList(String text) {
        List<Pedido> filteredList = new ArrayList<>();
        for (Pedido pedido : pedidosDb) {
            if (pedido.getCliente().getNome().toLowerCase().contains(text.toLowerCase())) {

                filteredList.add(pedido);

            }
        }

        if (filteredList.isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(getView(), R.string.SearchNotFound, Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            pedidoAdapter.setFilteredList(filteredList);
        }

    }

}