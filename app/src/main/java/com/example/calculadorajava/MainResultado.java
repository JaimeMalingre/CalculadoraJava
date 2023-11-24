package com.example.calculadorajava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class MainResultado extends AppCompatActivity {

    TextView resultadoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainresultado);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        resultadoView = findViewById(R.id.resultadoView);

        // Obtener el resultado del Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("resultado")) {
            String resultado = intent.getStringExtra("resultado");
            resultadoView.setText(resultado);
        }
    }
}
