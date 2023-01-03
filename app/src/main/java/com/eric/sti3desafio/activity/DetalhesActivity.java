package com.eric.sti3desafio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.eric.sti3desafio.R;
import com.eric.sti3desafio.adapter.ItemAdapter;
import com.eric.sti3desafio.adapter.PagamentoAdapter;
import com.eric.sti3desafio.model.Pedido;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetalhesActivity extends AppCompatActivity {

    private RecyclerView recyclerItens, recyclerPagamentos;

    private TextView txvRua, txvCliente, txvNumeroEndereco, txvCep,
            txvBairro, txvCidade, txvEstado, txvComplemento,
            txvReferencia, txvDocumento, txvNascimento, txvEmail,
            txvDesconto, txvFrete, txvSubtotal, txvTotal, txvCodigo,
            txvCriacao, txvAlteracao, txvstatus;

    private LottieAnimationView animationView;

    Date dataCriacaoFormatada, dataAlteracaoFormatada, dataNascimento;
    Animation animation;
    RelativeLayout relativeMain;

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
        txvCriacao = findViewById(R.id.txvCriacao);
        txvAlteracao = findViewById(R.id.txvAlteracao);
        txvstatus = findViewById(R.id.txvstatus);
        animationView = findViewById(R.id.animationView);

        recyclerItens = findViewById(R.id.recyclerItens);
        recyclerPagamentos = findViewById(R.id.recyclerPagamentos);

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_details);
        relativeMain = findViewById(R.id.relativeMain);
        relativeMain.setAnimation(animation);


        ImageView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Item Recycler

        LinearLayoutManager ItemsLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerItens.setLayoutManager(ItemsLayoutManager);
        recyclerItens.setNestedScrollingEnabled(false);
        recyclerItens.setHasFixedSize(true);

        //Payments Recycler

        LinearLayoutManager PaymentLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerPagamentos.setLayoutManager(PaymentLayoutManager);
        recyclerPagamentos.setNestedScrollingEnabled(false);
        recyclerPagamentos.setHasFixedSize(true);

        getData();
    }

    public void getData() {

        Intent i = getIntent();
        Pedido pedido = (Pedido) i.getSerializableExtra("pedidoData");

        ItemAdapter itemAdapter = new ItemAdapter(pedido.getItens(), this);
        recyclerItens.setAdapter(itemAdapter);

        PagamentoAdapter pagamentoAdapter = new PagamentoAdapter(pedido.getPagamento(), this);
        recyclerPagamentos.setAdapter(pagamentoAdapter);

        if (pedido.getStatus().equals("APROVADO")){
            txvstatus.setText("Pedido aprovado");
            animationView.setAnimation(R.raw.aprovado);
        } else {
            txvstatus.setText("Pedido cancelado");
            animationView.setAnimation(R.raw.cancelado);
        }

        //Endereço
        txvRua.setText(pedido.getEnderecoEntrega().getEndereco());
        txvNumeroEndereco.setText(pedido.getEnderecoEntrega().getNumero());
        txvCep.setText(pedido.getEnderecoEntrega().getCep());
        txvCidade.setText(pedido.getEnderecoEntrega().getCidade());
        txvEstado.setText(pedido.getEnderecoEntrega().getEstado());
        //
        if (pedido.getEnderecoEntrega().getComplemento().equals(""))
            txvComplemento.setText(R.string.default_details);
        else
            txvComplemento.setText(pedido.getEnderecoEntrega().getComplemento());
        //
        if (pedido.getEnderecoEntrega().getReferencia().equals(""))
            txvReferencia.setText(R.string.default_details);
        else
            txvReferencia.setText(pedido.getEnderecoEntrega().getReferencia());
        //
        if (pedido.getEnderecoEntrega().getBairro().equals(""))
            txvBairro.setText(R.string.default_details);
        else
        txvBairro.setText(pedido.getEnderecoEntrega().getBairro());

        //Cliente
        txvCliente.setText(pedido.getCliente().getNome());

        if (pedido.getCliente().getCnpj().equals("")) {
            txvDocumento.setText(pedido.getCliente().getCpf());
        }else {
            txvDocumento.setText(pedido.getCliente().getCnpj());
        }

        Locale brasil = new Locale("pt", "BR");

        String dataCriacao = pedido.getDataCriacao().substring(0,10);
        String dataAlteracao = pedido.getDataAlteracao().substring(0,10);
        //converte a string para Date
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd", brasil);
        try {
            dataCriacaoFormatada = parser.parse(dataCriacao);
            dataAlteracaoFormatada = parser.parse(dataAlteracao);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd MMM yyyy", brasil);
        String dataCriacaoFinal = simpleDateFormat.format(dataCriacaoFormatada);
        String dataAlteracaoFinal = simpleDateFormat.format(dataAlteracaoFormatada);
        txvCriacao.setText(dataCriacaoFinal);
        txvAlteracao.setText(dataAlteracaoFinal);

        //Nascimento
        String dataNasc = pedido.getCliente().getDataNascimento();

        //converte a string para Date
        try {
            dataNascimento = parser.parse(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //novo formato
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", brasil);
        String dataFormatada = formatter.format(dataNascimento);

        txvNascimento.setText(dataFormatada);

        txvEmail.setText(pedido.getCliente().getEmail());

        //Order
        txvDesconto.setText("R$ "+pedido.getDesconto());
        txvFrete.setText("R$ "+pedido.getFrete());
        txvSubtotal.setText("R$ "+pedido.getSubTotal());
        txvTotal.setText("R$ "+pedido.getValorTotal());
        txvCodigo.setText("Número "+pedido.getNumero());

    }
}