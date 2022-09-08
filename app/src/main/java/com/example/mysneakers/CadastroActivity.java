package com.example.mysneakers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextNomeSnk, editTextMarca, editTextTamanho, editTextColorway, editTextPrecoOg, editTextPrecoRev;
    private CheckBox cbPossui;
    private RadioGroup radioGroupEstado;
    private Spinner spinnerTamanhos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNomeSnk = findViewById(R.id.editTextSnkNome);
        editTextMarca = findViewById(R.id.editTextSnkMarca);
        editTextTamanho = findViewById(R.id.editTextTamanho);
        editTextColorway = findViewById(R.id.editTextSnkColorway);
        editTextPrecoOg = findViewById(R.id.editTextPrecoOg);
        editTextPrecoRev = findViewById(R.id.editTextPrecoRev);

        cbPossui = findViewById(R.id.checkBoxPossui);

        radioGroupEstado = findViewById(R.id.radioGroupEstado);

        spinnerTamanhos = findViewById(R.id.spinnerTamanhos);

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


        String ling = (String) spinnerTamanhos.getSelectedItem();
        if (ling != null){
            spMensagem = getString(R.string.tamtipo) + ling;
        }else{
            spMensagem = getString(R.string.tamtiponenhum);
        }


        switch (radioGroupEstado.getCheckedRadioButtonId()){
            case R.id.radioButtonNovo:
                rbMensagem = getString(R.string.snknovo);
                break;
            case R.id.radioButtonPUsado:
                rbMensagem = getString(R.string.snkpusado);
                break;
            case R.id.radioButtonMUsado:
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

    public static void novoSneaker(AppCompatActivity activity){

        Intent intent = new Intent(activity, MainActivity.class);

//        intent.putExtra(MODO, NOVO);
//
//        activity.startActivityForResult(intent, NOVO);
    }

}