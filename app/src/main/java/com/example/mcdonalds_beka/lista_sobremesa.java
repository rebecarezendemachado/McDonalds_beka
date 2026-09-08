package com.example.mcdonalds_beka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class lista_sobremesa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_lista_sobremesa);

        // Ajuste das margens da tela
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // ==========================================
        // VOLTAR PARA O MENU
        // ==========================================

        TextView btnVoltar1 = findViewById(R.id.btnVoltar1);

        btnVoltar1.setOnClickListener(v -> {

            Intent intent = new Intent(
                    lista_sobremesa.this,
                    home_mc.class
            );

            startActivity(intent);
        });
    }
}