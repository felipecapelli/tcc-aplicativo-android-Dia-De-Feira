package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton botaoFeiras;
    private ImageButton botaoProdutos;
    private ImageButton botaoProdutores;
    private ImageButton botaoReservas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoFeiras = (ImageButton) findViewById(R.id.activity_main_botao_feiras);
        botaoFeiras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent relatorioFeiras = new Intent(MainActivity.this, ListaFeirasActivity.class);
                startActivity(relatorioFeiras);
            }
        });

        botaoProdutos = (ImageButton) findViewById(R.id.activity_main_botao_produtos);
        botaoProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent relatorioProdutos = new Intent(MainActivity.this, ListaProdutosActivity.class);
                startActivity(relatorioProdutos);
            }
        });

        botaoProdutores = (ImageButton) findViewById(R.id.activity_main_botao_produtores);
        botaoProdutores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent relatorioProdutores = new Intent(MainActivity.this, ListaProdutoresActivity.class);
                startActivity(relatorioProdutores);
            }
        });

        botaoReservas = (ImageButton) findViewById(R.id.activity_main_botao_reservas);
        botaoReservas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Intent relatoriosReservas = new Intent (MainActivity.this, LoginActivity.class);
               startActivity(relatoriosReservas);
            }
        });
    }
}
