package com.example.proyectoiot.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import models.HealthData;
import viewmodel.HealthViewModel;

public class MainActivity extends AppCompatActivity {

    private HealthViewModel vm;
    private TextView tvPasosHoy, tvPasosTotales, tvOxigeno, tvPulso, tvRitmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPasosHoy = findViewById(R.id.tvPasosHoy);
        tvPasosTotales = findViewById(R.id.tvPasosTotales);
        tvOxigeno = findViewById(R.id.tvOxigeno);
        tvPulso = findViewById(R.id.tvPulso);
        tvRitmo = findViewById(R.id.tvRitmo);

        vm = new ViewModelProvider(this).get(HealthViewModel.class);

        vm.getHealthData().observe(this, hd -> {
            if (hd != null) {
                tvPasosHoy.setText(String.valueOf(hd.getPasosHoy()));
                tvPasosTotales.setText(String.valueOf(hd.getPasosTotales()));
                tvOxigeno.setText(String.valueOf(hd.getOxigeno()));
                tvPulso.setText(String.valueOf(hd.getPulso()));
                tvRitmo.setText(String.valueOf(hd.getRitmo()));
            }
        });

        findViewById(R.id.btnRefresh).setOnClickListener(v -> vm.refresh());
    }
}
