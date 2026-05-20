package com.example.tugas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Banner Matkul klik (Ke DetailKuliahActivity)
        ImageView banner = findViewById(R.id.bannerkuliah);
        if (banner != null) {
            banner.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, DetailKuliahActivity.class);
                startActivity(intent);
            });
        }

        // Tombol Tampilkan Semua klik (Ke MainActivity untuk lihat database)
        Button btnSemua = findViewById(R.id.btnSemua);
        if (btnSemua != null) {
            btnSemua.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            });
        }
    }
}