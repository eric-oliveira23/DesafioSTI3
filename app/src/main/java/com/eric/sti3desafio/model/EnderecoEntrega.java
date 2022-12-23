package com.eric.sti3desafio.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "enderecos",
        foreignKeys = {
                @ForeignKey(
                        entity = Pedido.class,
                        parentColumns = {"id"},
                        childColumns = {"EnderecoIdMap"},
                        onDelete = CASCADE,
                        onUpdate = CASCADE
                )
        }
)
public class EnderecoEntrega implements Serializable {

    @PrimaryKey
    Long EnderecoRoomId = null;

    @ColumnInfo(index = true)
    String EnderecoIdMap;

    @ColumnInfo(name = "idEntrega")
    private String id;

    @ColumnInfo(name = "endereco")
    private String endereco;

    @ColumnInfo(name = "numeroEntrega")
    private String numero;

    @ColumnInfo(name = "cep")
    private String cep;

    @ColumnInfo(name = "bairro")
    private String bairro;

    @ColumnInfo(name = "cidade")
    private String cidade;

    @ColumnInfo(name = "complemento")
    private String complemento;

    @ColumnInfo(name = "referencia")
    private String referencia;

    @ColumnInfo(name = "estado")
    private String estado;

    public EnderecoEntrega() {}

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

    public Long getEnderecoRoomId() {
        return EnderecoRoomId;
    }

    public void setEnderecoRoomId(Long enderecoRoomId) {
        EnderecoRoomId = enderecoRoomId;
    }

    public String getEnderecoIdMap() {
        return EnderecoIdMap;
    }

    public void setEnderecoIdMap(String enderecoIdMap) {
        EnderecoIdMap = enderecoIdMap;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
