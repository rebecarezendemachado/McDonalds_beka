package com.example.mcdonalds_beka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home_mc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_mc);

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


        // IMAGEM DO HAMBÚRGUER
        ImageView imagemBurger = findViewById(R.id.imageView6);

        imagemBurger.setOnClickListener(v -> {
            Intent intent = new Intent(home_mc.this, lista_hamburguer.class);
            startActivity(intent);
        });


        // IMAGEM BATATA
        ImageView imagemBatata = findViewById(R.id.imageView7);

        imagemBatata.setOnClickListener(v -> {
            Intent intent = new Intent(home_mc.this, lista_batatas.class);
            startActivity(intent);
        });


        // IMAGEM FRANGO
        ImageView imagemFrango = findViewById(R.id.imageView8);

        imagemFrango.setOnClickListener(v -> {
            Intent intent = new Intent(home_mc.this, lista_frango.class);
            startActivity(intent);
        });


        // IMAGEM BEBIDA
        ImageView imagemBebida = findViewById(R.id.imageView9);

        imagemBebida.setOnClickListener(v -> {
            Intent intent = new Intent(home_mc.this, lista_bebidas.class);
            startActivity(intent);
        });


        // IMAGEM SOBREMESA
        ImageView imagemSobremesa = findViewById(R.id.imageView10);

        imagemSobremesa.setOnClickListener(v -> {
            Intent intent = new Intent(home_mc.this, lista_sobremesa.class);
            startActivity(intent);
        });


        // RETIRAR EM
        CardView btnRetirar = findViewById(R.id.btnRetirar);

        btnRetirar.setOnClickListener(v -> {
            Intent intent = new Intent(home_mc.this, gps.class);
            startActivity(intent);
        });

    }
}