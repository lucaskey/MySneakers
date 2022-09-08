package com.example.mysneakers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SobreActivity extends AppCompatActivity {

    public static void sobre(AppCompatActivity activity){
        Intent intent = new Intent(activity, SobreActivity.class);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        setTitle(R.string.sobre);
    }

    public void abreSiteUTFPR(View view) {
        abreSite("http://www.utfpr.edu.br");
    }

    private void abreSite(String site) {
        Intent intent2 = new Intent(Intent.ACTION_VIEW);

        intent2.setData(Uri.parse(site));

        if (intent2.resolveActivity(getPackageManager()) != null) {
            startActivity(intent2);
        }else {
            Toast.makeText(this, R.string.erro_abrir_site, Toast.LENGTH_LONG).show();
        }
    }

}