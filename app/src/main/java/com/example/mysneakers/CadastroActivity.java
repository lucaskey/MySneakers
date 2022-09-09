package com.example.mysneakers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroActivity extends AppCompatActivity {



    private EditText editTextNomeSnk, editTextMarca, editTextTamanho, editTextColorway, editTextPrecoOg, editTextPrecoRev;
    private CheckBox cbPossui;
    private RadioGroup radioGroupEstado;
    private Spinner spinnerTamanhos;

    public static final String MODO    = "MODO";
    public static final String NOME    = "NOME";
    public static final String MARCA = "MARCA";
    public static final String COLORWAY = "COLORWAY";
    public static final String TIPOTAMANHO = "TIPOTAMANHO";
    public static final String TAMANHO = "TAMANHO";
    public static final String PRECOOG = "PRECOOG";
    public static final String PRECOREV = "PRECOREV";
    public static final String ESTADOSNK = "ESTADOSNK";
    public static final String POSSUISNK = "POSSUISNK";

    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;

    private int modo;

    public static void novoSneaker(AppCompatActivity activity){

        Intent intent = new Intent(activity, CadastroActivity.class);

        intent.putExtra(MODO, NOVO);

        activity.startActivityForResult(intent, NOVO);
    }

    public static void alterarSneaker(AppCompatActivity activity, Sneakers sneakers){

        Intent intent = new Intent(activity, CadastroActivity.class);

        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(MARCA, sneakers.getMarca());
        intent.putExtra(NOME, sneakers.getNome());
        intent.putExtra(COLORWAY, sneakers.getColorway());
        intent.putExtra(TIPOTAMANHO, sneakers.getPrecoRev());
        intent.putExtra(TAMANHO, sneakers.getTamanho());
        intent.putExtra(PRECOOG, sneakers.getPrecoOg());
        intent.putExtra(PRECOREV, sneakers.getPrecoRev());
        intent.putExtra(ESTADOSNK, sneakers.getEstadosnk());
        intent.putExtra(POSSUISNK, sneakers.isPossuisnk());

        activity.startActivityForResult(intent, ALTERAR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNomeSnk = findViewById(R.id.editTextSnkNome);
        editTextMarca = findViewById(R.id.editTextSnkMarca);
        editTextColorway = findViewById(R.id.editTextSnkColorway);
        spinnerTamanhos = findViewById(R.id.spinnerTamanhos);
        editTextTamanho = findViewById(R.id.editTextTamanho);
        editTextPrecoOg = findViewById(R.id.editTextPrecoOg);
        editTextPrecoRev = findViewById(R.id.editTextPrecoRev);
        radioGroupEstado = findViewById(R.id.radioGroupEstado);
        cbPossui = findViewById(R.id.checkBoxPossui);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){

            modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO){
                setTitle(getString(R.string.novo_snk));
            }
            else{
                String marcaSnk = bundle.getString(MARCA);
                editTextMarca.setText(marcaSnk);
                String nomeSnk = bundle.getString(NOME);
                editTextNomeSnk.setText(nomeSnk);
                String colorwaySnk = bundle.getString(COLORWAY);
                editTextColorway.setText(colorwaySnk);
                String tipoTamanhoSnk = bundle.getString(TIPOTAMANHO);
                String tamanhoSnk = bundle.getString(TAMANHO);
                editTextTamanho.setText(tamanhoSnk);
                String precoOgSnk = bundle.getString(PRECOOG);
                editTextPrecoOg.setText(precoOgSnk);
                String precoRevSnk = bundle.getString(PRECOREV);
                editTextPrecoRev.setText(precoRevSnk);
                int estadoSnk = bundle.getInt(ESTADOSNK);
                RadioButton button;
                switch(estadoSnk){
                    case Sneakers.NOVO:
                        button = findViewById(R.id.radioButtonNovo);
                        button.setChecked(true);
                        break;

                    case Sneakers.POUCO:
                        button = findViewById(R.id.radioButtonPUsado);
                        button.setChecked(true);
                        break;

                    case Sneakers.MUITO:
                        button = findViewById(R.id.radioButtonMUsado);
                        button.setChecked(true);
                        break;
                }
                Boolean possuiSnk = bundle.getBoolean(POSSUISNK);
                                for (int pos = 0; 0 < spinnerTamanhos.getAdapter().getCount(); pos++){
                    String valor = (String) spinnerTamanhos.getItemAtPosition(pos);

                    if (valor.equals(tipoTamanhoSnk)){
                        spinnerTamanhos.setSelection(pos);
                        break;
                    }
                }
                cbPossui.setChecked(possuiSnk);

                setTitle(getString(R.string.alt_snk));
            }
        }

        editTextMarca.requestFocus();

        popularSpinner();

    }

    public void popularSpinner(){
        ArrayList<String> lista = new ArrayList<>();

        lista.add(getString(R.string.tamcm));
        lista.add(getString(R.string.tamus));
        lista.add(getString(R.string.tambr));
        lista.add(getString(R.string.tamuk));
        lista.add(getString(R.string.tameur));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

        spinnerTamanhos.setAdapter(adapter);
    }

    public void salvarCampos(View view){
        String nomeSnk = editTextNomeSnk.getText().toString();
        String marca = editTextMarca.getText().toString();
        String colorway = editTextColorway.getText().toString();
        String tamanho = editTextTamanho.getText().toString();
        String precoOg = editTextPrecoOg.getText().toString();
        String precoRev = editTextPrecoRev.getText().toString();
        int estadoSnk = 0;
        boolean possuiSnk = cbPossui.isChecked();

        String cbMensagem = "";

        String rbMensagem = "";

        String spMensagem = "";


        if(marca == null||marca.trim().isEmpty()){
            Toast.makeText(this, R.string.erro_marca, Toast.LENGTH_LONG).show();
            editTextMarca.requestFocus();
            return;
        }

        if(nomeSnk == null||nomeSnk.trim().isEmpty()) {
            Toast.makeText(this, R.string.erro_nomesnk, Toast.LENGTH_LONG).show();
            editTextNomeSnk.requestFocus();
            return;
        }


        String tipoTamanhoSnk = (String) spinnerTamanhos.getSelectedItem();
        if (tipoTamanhoSnk != null){
            spMensagem = getString(R.string.tamtipo) + tipoTamanhoSnk;
        }else{
            spMensagem = getString(R.string.tamtiponenhum);
        }


        switch (radioGroupEstado.getCheckedRadioButtonId()){
            case R.id.radioButtonNovo:
                estadoSnk = Sneakers.NOVO;
                rbMensagem = getString(R.string.snknovo);
                break;
            case R.id.radioButtonPUsado:
                estadoSnk = Sneakers.POUCO;
                rbMensagem = getString(R.string.snkpusado);
                break;
            case R.id.radioButtonMUsado:
                estadoSnk = Sneakers.MUITO;
                rbMensagem = getString(R.string.snkmusado);
                break;
            default:
                rbMensagem = getString(R.string.snkestnselecionado);

        }


        if (cbPossui.isChecked()){
            cbMensagem += getString(R.string.cbpossui);
        }
        if (cbMensagem.equals("")){
            cbMensagem = getString(R.string.cbnpossui);
        }


        Toast.makeText(this,
                        marca + "\n" + nomeSnk + "\n" + colorway + "\n" + spMensagem + "\n" + tamanho + "\n" + precoOg + "\n" + precoRev + "\n" + rbMensagem + "\n" + cbMensagem,
                        Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.putExtra(MARCA,  marca);
        intent.putExtra(NOME,  nomeSnk);
        intent.putExtra(COLORWAY, colorway);
        intent.putExtra(TIPOTAMANHO, tipoTamanhoSnk);
        intent.putExtra(TAMANHO,  tamanho);
        intent.putExtra(PRECOOG, precoOg);
        intent.putExtra(PRECOREV, precoRev);
        intent.putExtra(ESTADOSNK, estadoSnk);
        intent.putExtra(POSSUISNK, possuiSnk);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    public void limparCampos(View view) {
        editTextNomeSnk.setText(null);
        editTextMarca.setText(null);
        editTextTamanho.setText(null);
        editTextColorway.setText(null);
        editTextPrecoOg.setText(null);
        editTextPrecoRev.setText(null);

        cbPossui.setChecked(false);

        radioGroupEstado.clearCheck();

        editTextMarca.requestFocus();

        Toast.makeText(this, R.string.campo_limpos, Toast.LENGTH_LONG).show();
    }



}