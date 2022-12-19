package com.eric.sti3desafio.api;

import com.eric.sti3desafio.model.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/pedido")
    Call<List<Pedido>> recuperarPedidos();

}
