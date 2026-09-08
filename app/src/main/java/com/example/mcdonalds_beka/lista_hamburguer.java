package com.example.mcdonalds_beka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class lista_hamburguer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_hamburguer);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        // ================================
        // IMAGEM DO BIG MAC
        // ================================

        ImageView imgBigMac = findViewById(R.id.imgBigMac);

        imgBigMac.setOnClickListener(v -> {

            Intent intent = new Intent(
                    lista_hamburguer.this,
                    hamburguer.class
            );

            startActivity(intent);
        });

//voltar pro menu
            TextView btnVoltar = findViewById(R.id.btnVoltar);

            btnVoltar.setOnClickListener(v -> {
                Intent intent = new Intent(lista_hamburguer.this, home_mc.class);
                startActivity(intent);
                finish();
            });

// ir para hamburguer
        TextView btnBigMac = findViewById(R.id.btnBigMac);

        btnBigMac.setOnClickListener(v -> {
            Intent intent = new Intent(lista_hamburguer.this, hamburguer.class);
            startActivity(intent);
            finish();
        });
        }
    }
