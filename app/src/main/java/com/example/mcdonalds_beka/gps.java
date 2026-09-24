package com.example.mcdonalds_beka;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

import java.util.Map;

public class gps extends AppCompatActivity {

    private Button btnLocalizacao;
    private TextView textGps;
    private WebView webViewMapa;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    private ActivityResultLauncher<String[]> localizacaoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        // ==========================================
        // BOTÃO VOLTAR
        // ==========================================

        TextView btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(v -> {

            Intent intent = new Intent(gps.this, home_mc.class);
            startActivity(intent);
            finish();

        });

        // ==========================================
        // ELEMENTOS DA TELA
        // ==========================================

        btnLocalizacao = findViewById(R.id.btnLocalizacao);

        // ==========================================
        // LOCALIZAÇÃO
        // ==========================================

        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);

        // ==========================================
        // WEBVIEW DO MAPA
        // ==========================================

        webViewMapa = findViewById(R.id.webViewMapa);

        WebSettings webSettings = webViewMapa.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webViewMapa.loadUrl("file:///android_asset/mapa.html");

        // ==========================================
        // RECEBE A LOCALIZAÇÃO
        // ==========================================

        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {

                if (locationResult == null) {
                    return;
                }

                for (Location location : locationResult.getLocations()) {

                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    // Atualiza o botão
                    btnLocalizacao.setText(
                            "Localização encontrada"
                    );

                    // Envia a localização para o mapa
                    webViewMapa.evaluateJavascript(
                            "updateLocation("
                                    + latitude
                                    + ","
                                    + longitude
                                    + ")",
                            null
                    );
                }

                // Para de buscar depois de encontrar
                fusedLocationClient.removeLocationUpdates(
                        locationCallback
                );
            }
        };

        // ==========================================
        // PEDIDO DE PERMISSÃO
        // ==========================================

        localizacaoLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.RequestMultiplePermissions(),
                        resultado -> {

                            Boolean fineConcedida =
                                    resultado.get(
                                            Manifest.permission.ACCESS_FINE_LOCATION
                                    );

                            Boolean coarseConcedida =
                                    resultado.get(
                                            Manifest.permission.ACCESS_COARSE_LOCATION
                                    );

                            if (Boolean.TRUE.equals(fineConcedida)
                                    || Boolean.TRUE.equals(coarseConcedida)) {

                                solicitarAtualizacaoLocalizacao();

                            } else {

                                btnLocalizacao.setText(
                                        "Permissão de localização negada"
                                );
                            }
                        }
                );

        // ==========================================
        // CLIQUE NO BOTÃO
        // ==========================================

        btnLocalizacao.setOnClickListener(v -> {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {

                solicitarAtualizacaoLocalizacao();

            } else {

                localizacaoLauncher.launch(
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        }
                );
            }
        });
    }

    // ==========================================
    // BUSCAR LOCALIZAÇÃO
    // ==========================================

    private void solicitarAtualizacaoLocalizacao() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        btnLocalizacao.setText(
                "Buscando localização..."
        );

        LocationRequest locationRequest =
                new LocationRequest.Builder(
                        Priority.PRIORITY_HIGH_ACCURACY,
                        2000
                )
                        .setMaxUpdates(1)
                        .build();

        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
        );
    }
}