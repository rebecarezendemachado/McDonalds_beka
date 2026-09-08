package com.example.mcdonalds_beka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class hamburguer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburguer);

        TextView btnVoltar2 = findViewById(R.id.btnVoltar2);

        btnVoltar2.setOnClickListener(v -> {
            Intent intent = new Intent(hamburguer.this, lista_hamburguer.class);
            startActivity(intent);
            finish();
        });
    }
}
