package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diadefeira.adapter.ListaFeirasAdapter;
import com.example.diadefeira.modelo.DetalhesFeira;
import com.example.diadefeira.task.FeirasTask;

public class ListaFeirasActivity extends AppCompatActivity {

    private ListView listViewFeiras;
    private Button botaoBusca;
    private EditText textoBusca;
    private int contaMudancasNoTextoBusca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_feiras);

        listViewFeiras = (ListView) findViewById(R.id.activity_lista_feiras_lista_feiras);
        botaoBusca = (Button) findViewById(R.id.activity_lista_feiras_botao_busca);
        textoBusca = (EditText) findViewById(R.id.activity_lista_feiras_texto_busca);

        contaMudancasNoTextoBusca = 0;
        textoBusca.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textoBusca.setText("");
                    contaMudancasNoTextoBusca++;//pra apagar apenas uma vez o texto
                }
            }
        });

        botaoBusca.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FeirasTask feirasTask = new FeirasTask(textoBusca.getText().toString(), listViewFeiras, ListaFeirasActivity.this);
                feirasTask.execute();
            }
        });

        listViewFeiras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                TextView campoId = item.findViewById(R.id.item_lista_feiras_id);
                TextView campoNome = item.findViewById(R.id.item_lista_feiras_nome);
                TextView campoData = item.findViewById(R.id.item_lista_feiras_data);
                TextView campoLocal = item.findViewById(R.id.item_lista_feiras_local);

                DetalhesFeira feira = new DetalhesFeira(
                        Long.parseLong(campoId.getText().toString()),
                        campoNome.getText().toString(),
                        campoLocal.getText().toString(),
                        campoData.getText().toString()
                );

                Intent intentVaiParaDetalhesDaFeira = new Intent(ListaFeirasActivity.this, DetalhesDaFeiraActivity.class);
                intentVaiParaDetalhesDaFeira.putExtra("feira", feira);
                startActivity(intentVaiParaDetalhesDaFeira);
            }
        });

    }
}
