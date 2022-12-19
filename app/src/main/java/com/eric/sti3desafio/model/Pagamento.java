package com.eric.sti3desafio.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pagamento implements Parcelable {

    private String id;
    private String parcela;
    private String valor;
    private String codigo;
    private String nome;

    protected Pagamento(Parcel in) {
        id = in.readString();
        parcela = in.readString();
        valor = in.readString();
        codigo = in.readString();
        nome = in.readString();
    }

    public static final Creator<Pagamento> CREATOR = new Creator<Pagamento>() {
        @Override
        public Pagamento createFromParcel(Parcel in) {
            return new Pagamento(in);
        }

        @Override
        public Pagamento[] newArray(int size) {
            return new Pagamento[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(parcela);
        parcel.writeString(valor);
        parcel.writeString(codigo);
        parcel.writeString(nome);
    }
}
