package com.eric.sti3desafio.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.eric.sti3desafio.model.Pedido;

import java.util.List;

@Dao
public interface PedidoDAO {

    @Query("SELECT * FROM pedidos")
    List<Pedido> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Pedido> pedidos);

    @Update
    void update(Pedido pedido);

}
