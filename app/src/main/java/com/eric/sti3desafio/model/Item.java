package com.eric.sti3desafio.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "itens",
        foreignKeys = {
                @ForeignKey(
                        entity = Pedido.class,
                        parentColumns = {"id"},
                        childColumns = {"ItemIdMap"},
                        onDelete = CASCADE,
                        onUpdate = CASCADE
                )
        }
)
public class Item implements Serializable {

    @PrimaryKey
    Long ItemRoomId = null;

    @ColumnInfo(index = true)
    String ItemIdMap;

    @ColumnInfo(name = "idItem")
    private String id;

    @ColumnInfo(name = "idPedido")
    private String idPedido;

    @ColumnInfo(name = "nomeItem")
    private String nome;

    @ColumnInfo(name = "quantidade")
    private String quantidade;

    @ColumnInfo(name = "valorUnitario")
    private String valorUnitario;

    public Item() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Long getItemRoomId() {
        return ItemRoomId;
    }

    public void setItemRoomId(Long itemRoomId) {
        ItemRoomId = itemRoomId;
    }

    public String getItemIdMap() {
        return ItemIdMap;
    }

    public void setItemIdMap(String itemIdMap) {
        ItemIdMap = itemIdMap;
    }
}
