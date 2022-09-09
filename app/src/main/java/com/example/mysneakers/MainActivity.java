package com.example.mysneakers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewSneakers;
    private ArrayAdapter<Sneakers> listAdapter;
    private ArrayList<Sneakers> listSneaker;

    private int  posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSneakers = findViewById(R.id.listViewSneakers);

        listViewSneakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
                alterarSneaker();

            }
        });

        listViewSneakers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
                alterarSneaker();
                return true;

            }
        });

        popularLista();

    }

    private void popularLista() {

        listSneaker = new ArrayList<>();

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSneaker);

        listViewSneakers.setAdapter(listAdapter);

    }

    public void abrirSobre(View view){
        SobreActivity.sobre(this);
    }

    public void adicionarSneaker(View view){
        CadastroActivity.novoSneaker(this);
    }

    private void alterarSneaker(){

        Sneakers sneakers = listSneaker.get(posicaoSelecionada);

        CadastroActivity.alterarSneaker(this, sneakers);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();

            String marcaSnk = bundle.getString(CadastroActivity.MARCA);
            String nomeSnk = bundle.getString(CadastroActivity.NOME);
            String colorwaySnk = bundle.getString(CadastroActivity.COLORWAY);
            String tipoTamanhoSnk = bundle.getString(CadastroActivity.TIPOTAMANHO);
            String tamanhoSnk = bundle.getString(CadastroActivity.TAMANHO);
            String precoOgSnk = bundle.getString(CadastroActivity.PRECOOG);
            String precoRevSnk = bundle.getString(CadastroActivity.PRECOREV);
            int estadoSnk = bundle.getInt(CadastroActivity.ESTADOSNK);
            boolean possuiSnk = bundle.getBoolean(CadastroActivity.POSSUISNK);


            if (requestCode == CadastroActivity.ALTERAR) {

                Sneakers sneakers = listSneaker.get(posicaoSelecionada);

                sneakers.setMarca(marcaSnk);
                sneakers.setNome(nomeSnk);
                sneakers.setColorway(colorwaySnk);
                sneakers.setTipotamanho(tipoTamanhoSnk);
                sneakers.setTamanho(tamanhoSnk);
                sneakers.setPrecoOg(precoOgSnk);
                sneakers.setPrecoRev(precoRevSnk);
                sneakers.setEstadosnk(estadoSnk);
                sneakers.setPossuisnk(possuiSnk);

                posicaoSelecionada = -1;

            } else {
                Sneakers sneakers = new Sneakers(marcaSnk, nomeSnk, colorwaySnk, tipoTamanhoSnk, tamanhoSnk, precoOgSnk, precoRevSnk, estadoSnk, possuiSnk);

                listSneaker.add(sneakers);
            }

            listAdapter.notifyDataSetChanged();
        }
    }

}
