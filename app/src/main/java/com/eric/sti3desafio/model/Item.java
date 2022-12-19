package com.eric.sti3desafio.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String id;
    private String idPedido;
    private String nome;
    private String quantidade;
    private String valorUnitario;

    protected Item(Parcel in) {
        id = in.readString();
        idPedido = in.readString();
        nome = in.readString();
        quantidade = in.readString();
        valorUnitario = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(idPedido);
        parcel.writeString(nome);
        parcel.writeString(quantidade);
        parcel.writeString(valorUnitario);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>()
    {
        public Item createFromParcel(Parcel in)
        {
            return new Item(in);
        }
        public Item[] newArray(int size)
        {
            return new Item[size];
        }
    };

}
