package com.eric.sti3desafio.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EnderecoEntrega implements Parcelable {

    private String id;
    private String endereco;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String complemento;
    private String referencia;

    protected EnderecoEntrega(Parcel in) {
        id = in.readString();
        endereco = in.readString();
        numero = in.readString();
        cep = in.readString();
        bairro = in.readString();
        cidade = in.readString();
        complemento = in.readString();
        referencia = in.readString();
    }

    public static final Creator<EnderecoEntrega> CREATOR = new Creator<EnderecoEntrega>() {
        @Override
        public EnderecoEntrega createFromParcel(Parcel in) {
            return new EnderecoEntrega(in);
        }

        @Override
        public EnderecoEntrega[] newArray(int size) {
            return new EnderecoEntrega[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(endereco);
        parcel.writeString(numero);
        parcel.writeString(cep);
        parcel.writeString(bairro);
        parcel.writeString(cidade);
        parcel.writeString(complemento);
        parcel.writeString(referencia);
    }
}
