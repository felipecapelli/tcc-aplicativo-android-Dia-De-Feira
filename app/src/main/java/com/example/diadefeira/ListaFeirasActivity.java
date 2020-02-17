package com.example.diadefeira;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
    }
}
