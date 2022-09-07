package com.example.mysneakers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ListView listViewSneakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listViewSneakers = findViewById(R.id.listViewSneakers);

        popularLista();

//        listViewSneakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//          @Override
//          public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//              Sneakers sneakers = (Sneakers) listViewSneakers.getItemAtPosition(position);
//              Toast.makeText(getApplicationContext(), sneakers.getMarca(), Toast.LENGTH_SHORT).show();
//          }
//        });

    }

    private void popularLista() {

        String[] nomes = getResources().getStringArray(R.array.marca);
        String[] marcas = getResources().getStringArray(R.array.nome);
        String[] tamanhos = getResources().getStringArray(R.array.tamanho);
        String[] colorways = getResources().getStringArray(R.array.colorway);

        ArrayList<Sneakers> sneakers = new ArrayList<>();

        for (int i = 0; i < nomes.length; i++) {
            sneakers.add(new Sneakers(nomes[i], marcas[i], tamanhos[i], colorways[i]));
        }

        SneakersAdapter sneakersAdapter = new SneakersAdapter(this, sneakers);

        listViewSneakers.setAdapter(sneakersAdapter);

    }

}

//        listViewSneakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//          @Override
//          public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//              Toast.makeText(getApplicationContext(), sneakers + getString(R.string.foi_clicado), Toast.LENGTH_SHORT).show();
//          }
//        });

//            Toast.makeText(this,
//                    "Marca= " + marcas[i] + "/n" +
//                    "Nome= " + nomes[i] + "/n" +
//                    "Colorway= " + colorways[i] + "/n" +
//                    "Tamanho= " + tamanhos[i],
//                    Toast.LENGTH_SHORT).show();