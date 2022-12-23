package com.eric.sti3desafio.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.eric.sti3desafio.database.DataConverter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "pedidos")
public class Pedido implements Serializable {

    @Embedded
    private Cliente cliente;

    @Embedded
    private EnderecoEntrega enderecoEntrega;

    @TypeConverters({DataConverter.class})
    private List<Item> itens;

    @TypeConverters({DataConverter.class})
    private List<Pagamento> pagamentos;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "numeroPedido")
    private int numero;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "dataCriacao")
    private String dataCriacao;

    @ColumnInfo(name = "dataAlteracao")
    private String dataAlteracao;

    @ColumnInfo(name = "desconto")
    private String desconto;

    @ColumnInfo(name = "frete")
    private String frete;

    @ColumnInfo(name = "subTotal")
    private String subTotal;

    @ColumnInfo(name = "valorTotal")
    private String valorTotal;

    public Pedido(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getFrete() {
        return frete;
    }

    public void setFrete(String frete) {
        this.frete = frete;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
