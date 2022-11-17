package com.example.mysneakers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.mysneakers.modelo.Sneakers;
import com.example.mysneakers.persistencia.SneakersDatabase;
import com.example.mysneakers.utils.UtilsGUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewSneakers;
    private ArrayAdapter<Sneakers> listAdapter;
    private List<Sneakers> listSneakers;

    private static final int REQUEST_NOVO_SNEAKERS    = 1;
    private static final int REQUEST_ALTERAR_SNEAKERS = 2;

    private int  posicaoSelecionada = -1;

    SwitchCompat switchCompat;
    SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSneakers = findViewById(R.id.listViewSneakers);

        switchCompat = findViewById(R.id.switchCompat);

        popularLista();

        registerForContextMenu(listViewSneakers);

        sharedPreferences = getSharedPreferences("night",0);

        Boolean booleanValue = sharedPreferences.getBoolean("night_mode",true);
        if (booleanValue){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchCompat.setChecked(true);
        }

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchCompat.setChecked(true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",true);
                    editor.commit();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchCompat.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",false);
                    editor.commit();

                }
            }
        });
    }

    private void popularLista() {

        SneakersDatabase database = SneakersDatabase.getDatabase(this);

        List<Sneakers> listSneakers = database.sneakersDAO().queryAll();

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSneakers);

        listViewSneakers.setAdapter(listAdapter);

    }

    private void alterarSneaker(){

        Sneakers sneakers = listSneakers.get(posicaoSelecionada);

        CadastroActivity.alterarSneaker(this, sneakers);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.principal_menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Sneakers sneakers = (Sneakers) listViewSneakers.getItemAtPosition(info.position);

        switch (item.getItemId()){
            case R.id.menuItemEditar:
                alterarSneaker();
                return true;

            case R.id.menuItemExcluir:
                excluirSneaker(sneakers);
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    private void excluirSneaker(final Sneakers sneakers) {
        String mensagem = getString(R.string.confirmar_pargar) + "\n" + sneakers.getNome();

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:

                                SneakersDatabase database =
                                        SneakersDatabase.getDatabase(MainActivity.this);

                                database.sneakersDAO().delete(sneakers);

                                listSneakers.remove(sneakers);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

        UtilsGUI.confirmaAcao(this, mensagem, listener);
    }


    private void verificaTipos(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                SneakersDatabase database = SneakersDatabase.getDatabase(MainActivity.this);

                int total = database.tipoDao().total();

                if (total == 0){

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilsGUI.avisoErro(MainActivity.this, R.string.nenhum_tipo);
                        }
                    });

                    return;
                }

                CadastroActivity.novoSneaker(MainActivity.this, REQUEST_NOVO_SNEAKERS);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemAdicionar:
                CadastroActivity.novoSneaker(this, REQUEST_NOVO_SNEAKERS);
                return true;

            case R.id.menuItemSobre:
                SobreActivity.sobre(this);
                return true;

            case R.id.menuItemNovo:
                verificaTipos();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();

            String marca = bundle.getString(CadastroActivity.MARCA);
            String nome = bundle.getString(CadastroActivity.NOME);
            String colorway = bundle.getString(CadastroActivity.COLORWAY);
            String tipoTamanho = bundle.getString(CadastroActivity.TIPOTAMANHO);
            String tamanho = bundle.getString(CadastroActivity.TAMANHO);
            String precoOg = bundle.getString(CadastroActivity.PRECOOG);
            String precoRev = bundle.getString(CadastroActivity.PRECOREV);
            int estado = bundle.getInt(CadastroActivity.ESTADOSNK);
            boolean possui = bundle.getBoolean(CadastroActivity.POSSUISNK);


            if (requestCode == CadastroActivity.ALTERAR) {

                Sneakers sneakers = listSneakers.get(posicaoSelecionada);

                sneakers.setMarca(marca);
                sneakers.setNome(nome);
                sneakers.setColorway(colorway);
                sneakers.setTipotamanho(tipoTamanho);
                sneakers.setTamanho(tamanho);
                sneakers.setPrecoOg(precoOg);
                sneakers.setPrecoRev(precoRev);
                sneakers.setEstadosnk(estado);
                sneakers.setPossuisnk(possui);

                posicaoSelecionada = -1;

            } else {
                Sneakers sneakers = new Sneakers(marca, nome, tipoTamanho, tamanho, colorway, precoOg, precoRev, estado, possui);

                listSneakers.add(sneakers);
            }

            listAdapter.notifyDataSetChanged();
        }
    }

}
