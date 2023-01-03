package com.eric.sti3desafio.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.eric.sti3desafio.dao.PedidoDAO;
import com.eric.sti3desafio.model.Cliente;
import com.eric.sti3desafio.model.EnderecoEntrega;
import com.eric.sti3desafio.model.Item;
import com.eric.sti3desafio.model.Pagamento;
import com.eric.sti3desafio.model.Pedido;

@Database(entities = {Pedido.class, Cliente.class, EnderecoEntrega.class, Item.class, Pagamento.class}, version = 11, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract PedidoDAO pedidoDAO();

}
