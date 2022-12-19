package com.eric.sti3desafio.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Pedido implements Parcelable {

    private Cliente cliente;
    private EnderecoEntrega enderecoEntrega;
    private List<Item> itens;
    private List<Pagamento> pagamentos;

    private int numero;
    private String status;
    private String id;
    private String dataCriacao;
    private String dataAlteracao;
    private String desconto;
    private String frete;
    private String subTotal;
    private String valorTotal;

    protected Pedido(Parcel in) {
        itens = in.createTypedArrayList(Item.CREATOR);
        numero = in.readInt();
        status = in.readString();
        valorTotal = in.readString();
        id = in.readString();
        dataCriacao = in.readString();
        dataAlteracao = in.readString();
        desconto = in.readString();
        frete = in.readString();
        subTotal = in.readString();
    }

    public static final Parcelable.Creator<Pedido> CREATOR = new Parcelable.Creator<Pedido>()
    {
        public Pedido createFromParcel(Parcel in)
        {
            return new Pedido(in);
        }
        public Pedido[] newArray(int size)
        {
            return new Pedido[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(itens);
        parcel.writeInt(numero);
        parcel.writeString(status);
        parcel.writeString(valorTotal);
        parcel.writeString(id);
        parcel.writeString(dataCriacao);
        parcel.writeString(dataAlteracao);
        parcel.writeString(frete);
        parcel.writeString(desconto);
        parcel.writeString(subTotal);
    }
}
