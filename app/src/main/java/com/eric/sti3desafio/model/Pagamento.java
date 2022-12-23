package com.eric.sti3desafio.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "pagamentos",
        foreignKeys = {
                @ForeignKey(
                        entity = Pedido.class,
                        parentColumns = {"id"},
                        childColumns = {"PagamentoIdMap"},
                        onDelete = CASCADE,
                        onUpdate = CASCADE
                )
        }
)

public class Pagamento implements Serializable {

    @PrimaryKey
    Long PagamentoRoomId = null;

    @ColumnInfo(index = true)
    String PagamentoIdMap;

    @ColumnInfo(name = "idPagamento")
    private String id;

    @ColumnInfo(name = "parcela")
    private String parcela;

    @ColumnInfo(name = "valor")
    private String valor;

    @ColumnInfo(name = "codigo")
    private String codigo;

    @ColumnInfo(name = "nomePagamento")
    private String nome;

    public Pagamento() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPagamentoRoomId() {
        return PagamentoRoomId;
    }

    public void setPagamentoRoomId(Long pagamentoRoomId) {
        PagamentoRoomId = pagamentoRoomId;
    }

    public String getPagamentoIdMap() {
        return PagamentoIdMap;
    }

    public void setPagamentoIdMap(String pagamentoIdMap) {
        PagamentoIdMap = pagamentoIdMap;
    }
}