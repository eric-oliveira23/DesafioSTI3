package com.eric.sti3desafio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.adapter.ItemAdapter;
import com.eric.sti3desafio.model.Pedido;

public class DetalhesActivity extends AppCompatActivity {

    private ImageView btnBack;
    private RecyclerView recyclerItens;

    private TextView txvRua, txvCliente, txvNumeroEndereco, txvCep,
            txvBairro, txvCidade, txvEstado, txvComplemento,
            txvReferencia, txvDocumento, txvNascimento, txvEmail,
            txvDesconto, txvFrete, txvSubtotal, txvTotal, txvCodigo;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txvRua = findViewById(R.id.txvRua);
        txvCliente = findViewById(R.id.txvCliente);
        txvNumeroEndereco = findViewById(R.id.txvNumeroEndereco);
        txvCep = findViewById(R.id.txvCep);
        txvBairro = findViewById(R.id.txvBairro);
        txvCidade = findViewById(R.id.txvCidade);
        txvEstado = findViewById(R.id.txvEstado);
        txvComplemento = findViewById(R.id.txvComplemento);
        txvReferencia = findViewById(R.id.txvReferencia);
        txvDocumento = findViewById(R.id.txvDocumento);
        txvNascimento = findViewById(R.id.txvNascimento);
        txvEmail = findViewById(R.id.txvEmail);
        txvDesconto = findViewById(R.id.txvDesconto);
        txvFrete = findViewById(R.id.txvFrete);
        txvSubtotal = findViewById(R.id.txvSubtotal);
        txvTotal = findViewById(R.id.txvTotal);
        txvCodigo = findViewById(R.id.txvCodigo);

        recyclerItens = findViewById(R.id.recyclerItens);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerItens.setLayoutManager(layoutManager);

        recyclerItens.setNestedScrollingEnabled(false);

        recyclerItens.setHasFixedSize(true);

        getData();
    }

    public void getData() {

        Intent i = getIntent();
        Pedido pedido = (Pedido) i.getSerializableExtra("pedidoData");

        itemAdapter = new ItemAdapter(pedido.getItens(), this);
        recyclerItens.setAdapter(itemAdapter);

        //endereço
        txvRua.setText(pedido.getEnderecoEntrega().getEndereco());
        txvNumeroEndereco.setText(pedido.getEnderecoEntrega().getNumero());
        txvCep.setText(pedido.getEnderecoEntrega().getCep());
        txvBairro.setText(pedido.getEnderecoEntrega().getBairro());
        txvCidade.setText(pedido.getEnderecoEntrega().getCidade());
        txvEstado.setText(pedido.getEnderecoEntrega().getEstado());
        txvComplemento.setText(pedido.getEnderecoEntrega().getComplemento());
        txvReferencia.setText(pedido.getEnderecoEntrega().getReferencia());

        //cliente
        txvCliente.setText(pedido.getCliente().getNome());

        if (pedido.getCliente().getCnpj().equals("")) {
            txvDocumento.setText(pedido.getCliente().getCpf());
        }else {
            txvDocumento.setText(pedido.getCliente().getCnpj());
        }

        txvNascimento.setText(pedido.getCliente().getDataNascimento());
        txvEmail.setText(pedido.getCliente().getEmail());

        //pedido
        txvDesconto.setText("R$ "+pedido.getDesconto());
        txvFrete.setText("R$ "+pedido.getFrete());
        txvSubtotal.setText("R$ "+pedido.getSubTotal());
        txvTotal.setText("R$ "+pedido.getValorTotal());
        txvCodigo.setText("Número "+pedido.getNumero());

    }
}