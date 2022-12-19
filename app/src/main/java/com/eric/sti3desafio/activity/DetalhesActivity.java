package com.eric.sti3desafio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.adapter.ItemAdapter;
import com.eric.sti3desafio.model.Item;

import java.util.ArrayList;

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

        ArrayList<Item> itemList;
        itemList = this.getIntent().getParcelableArrayListExtra("data");

        Bundle extras = getIntent().getExtras();
        //endereço
        String rua = extras.getString("rua");
        String numero = extras.getString("numero");
        String cep = extras.getString("cep");
        String bairro = extras.getString("bairro");
        String cidade = extras.getString("cidade");
        String estado = extras.getString("estado");
        String complemento = extras.getString("complemento");
        String referencia = extras.getString("referencia");

        //cliente
        String nome = extras.getString("nome");
        String documento = extras.getString("documento");
        String nascimento = extras.getString("nascimento");
        String email = extras.getString("email");

        //pedido
        String desconto = extras.getString("desconto");
        String frete = extras.getString("frete");
        String subtotal = extras.getString("subtotal");
        String valortotal = extras.getString("valortotal");
        String numeroPedido = extras.getString("numeroPedido");

        itemAdapter = new ItemAdapter(itemList, this);
        recyclerItens.setAdapter(itemAdapter);

        //endereço
        txvRua.setText(rua);
        txvNumeroEndereco.setText(numero);
        txvCep.setText(cep);
        txvBairro.setText(bairro);
        txvCidade.setText(cidade);
        txvEstado.setText(estado);
        txvComplemento.setText(complemento);
        txvReferencia.setText(referencia);

        //cliente
        txvCliente.setText(nome);
        txvDocumento.setText(documento);
        txvNascimento.setText(nascimento);
        txvEmail.setText(email);

        //pedido
        txvDesconto.setText("R$ "+desconto);
        txvFrete.setText("R$ "+frete);
        txvSubtotal.setText("R$ "+subtotal);
        txvTotal.setText("R$ "+valortotal);
        txvCodigo.setText("Número "+numeroPedido);

    }
}