package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.task.ProdutoTask;


public class ListaProdutosActivity extends AppCompatActivity {

    private Button botaoBusca;
    private TextView textViewProduto;
    private TextView textViewLocal;
    private ListView listViewProdutos;

    private int contaMudancasNoTextoProduto = 0;
    private int contaMudancasNoTextoLocal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        botaoBusca = (Button) findViewById(R.id.activity_lista_produtos_botao_busca);
        textViewProduto = (TextView) findViewById(R.id.activity_lista_produtos_texto_nome_produto);
        textViewLocal = (TextView) findViewById(R.id.activity_lista_produtos_texto_local);
        listViewProdutos = (ListView) findViewById(R.id.activity_lista_feiras_lista_produtos);

        textViewProduto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textViewProduto.setText("");
                    contaMudancasNoTextoProduto++;//pra apagar apenas uma vez o texto
                }
            }
        });

        textViewLocal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textViewLocal.setText("");
                    contaMudancasNoTextoLocal++;//pra apagar apenas uma vez o texto
                }
            }
        });

        botaoBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdutoTask produtoTask = new ProdutoTask(textViewProduto.getText().toString(), textViewLocal.getText().toString(), listViewProdutos, ListaProdutosActivity.this);
                produtoTask.execute();
            }
        });
    }
}
