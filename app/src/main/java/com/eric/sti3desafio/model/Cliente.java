package com.eric.sti3desafio.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "clientes",
        foreignKeys = {
                @ForeignKey(
                        entity = Pedido.class,
                        parentColumns = {"id"},
                        childColumns = {"clienteIdMap"},
                        onDelete = CASCADE,
                        onUpdate = CASCADE
                )
        }
)
public class Cliente implements Serializable {

    @PrimaryKey
    Long ClienteRoomId = null;

    @ColumnInfo(index = true)
    String clienteIdMap;

    @ColumnInfo(name = "idCliente")
    private String id;

    @ColumnInfo(name = "nomeCliente")
    private String nome;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "cnpj")
    private String cnpj;

    @ColumnInfo(name = "cpf")
    private String cpf;

    @ColumnInfo(name = "razao_social")
    private String razaoSocial;

    @ColumnInfo(name = "dataNascimento")
    private String dataNascimento;

    public Cliente() {}

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

    public Long getClienteRoomId() {
        return ClienteRoomId;
    }

    public void setClienteRoomId(Long clienteRoomId) {
        ClienteRoomId = clienteRoomId;
    }

    public String getClienteIdMap() {
        return clienteIdMap;
    }

    public void setClienteIdMap(String clienteIdMap) {
        this.clienteIdMap = clienteIdMap;
    }
}
