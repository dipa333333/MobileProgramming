package com.example.tugas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler; // Pindahkan import ke atas sini

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // 1. Pengaturan Full Screen (EdgeToEdge) agar logo pas di tengah
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. LOGIKA TIMER (HANDLER) - Harus di dalam onCreate
        new Handler().postDelayed(() -> {
            // Pindah dari Splash ke Login
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);

            // Menutup SplashActivity agar user tidak bisa balik lagi pakai tombol back
            finish();
        }, 2000); // 2000ms = 2 detik
    }
}