package com.eric.sti3desafio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.activity.DetalhesActivity;
import com.eric.sti3desafio.activity.OfflineActivity;
import com.eric.sti3desafio.adapter.PedidoAdapter;
import com.eric.sti3desafio.api.DataService;
import com.eric.sti3desafio.api.ServiceConnection;
import com.eric.sti3desafio.database.MyDatabase;
import com.eric.sti3desafio.model.Pedido;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PedidosFragment extends Fragment {

    private RecyclerView recyclerPedidos;
    private List<Pedido> pedidos = new ArrayList<>();
    private PedidoAdapter pedidoAdapter;
    Retrofit retrofit;
    private PedidoAdapter.RecyclerViewClickListener listener;
    private MyDatabase db;
    private List<Pedido> pedidosDb = new ArrayList<>();
    private ShimmerFrameLayout shimmerFrameLayout;
    ServiceConnection serviceConnection = new ServiceConnection();

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

        retrofit = serviceConnection.getBaseUrl();

        shimmerFrameLayout = getView().findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();


        recyclerPedidos = getView().findViewById(R.id.recyclerPedidos);

        pedidosClickListener();
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerPedidos.setLayoutManager(layoutManager);

        recyclerPedidos.setHasFixedSize(true);

        retrofitData();

    }

    private void retrofitData() {

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
                setAdapter();
                if (pedidosDb.isEmpty()){
                    startActivity(new Intent(getActivity(), OfflineActivity.class));
                }
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
        recyclerPedidos.setAdapter(pedidoAdapter);

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

}