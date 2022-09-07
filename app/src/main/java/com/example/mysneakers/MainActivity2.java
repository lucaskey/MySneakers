package com.example.mysneakers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ListView listViewSneakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listViewSneakers = findViewById(R.id.listViewSneakers);

        listViewSneakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Sneakers sneakersList = (Sneakers) listViewSneakers.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), sneakersList + "\n" + getString(R.string.foi_clicado), Toast.LENGTH_SHORT).show();
            }
        });

        popularLista();

    }

    private void popularLista() {

        String[] nomes = getResources().getStringArray(R.array.marca);
        String[] marcas = getResources().getStringArray(R.array.nome);
        String[] tamanhos = getResources().getStringArray(R.array.tamanho);
        String [] colorways = getResources().getStringArray(R.array.colorway);

        ArrayList<Sneakers> sneakers = new ArrayList<>();

        for (int i = 0; i < nomes.length; i++) {
            sneakers.add(new Sneakers(nomes[i], marcas[i], tamanhos[i], colorways[i]));
        }

        ArrayAdapter<Sneakers> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sneakers);

        listViewSneakers.setAdapter(adapter);

    }

}


//            Toast.makeText(this,
//                    "Marca= " + marcas[i] + "/n" +
//                    "Nome= " + nomes[i] + "/n" +
//                    "Colorway= " + colorways[i] + "/n" +
//                    "Tamanho= " + tamanhos[i],
//                    Toast.LENGTH_SHORT).show();