package com.eric.sti3desafio.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {

    private String id;
    private String nome;
    private String email;
    private String cnpj;
    private String cpf;
    private String razaoSocial;
    private String dataNascimento;

    protected Cliente(Parcel in) {
        nome = in.readString();
        email = in.readString();
        id = in.readString();
        cnpj = in.readString();
        cpf = in.readString();
        razaoSocial = in.readString();
        dataNascimento = in.readString();
    }

    public static final Parcelable.Creator<Cliente> CREATOR = new Parcelable.Creator<Cliente>()
    {
        public Cliente createFromParcel(Parcel in)
        {
            return new Cliente(in);
        }
        public Cliente[] newArray(int size)
        {
            return new Cliente[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(email);
        parcel.writeString(cnpj);
        parcel.writeString(cpf);
        parcel.writeString(razaoSocial);
        parcel.writeString(dataNascimento);
    }
}
