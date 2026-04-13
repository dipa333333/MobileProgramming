package com.example.tugas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView; // Import sudah aman

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

        // 1. Logika untuk padding layar agar tidak tertutup Notch/Status Bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); // Tutup listener Insets di sini

        // 2. INISIALISASI BANNER (Harus di luar listener insets, tapi tetap dalam onCreate)
        // Pastikan di activity_dashboard.xml ID-nya adalah "bannerkuliah" (huruf kecil semua)
        ImageView banner = findViewById(R.id.bannerkuliah);

        // 3. SET KLIK LISTENER
        if (banner != null) { // Tambahan aman biar nggak crash kalau ID salah
            banner.setOnClickListener(v -> {
                // Pindah ke DetailKuliahActivity
                Intent intent = new Intent(DashboardActivity.this, DetailKuliahActivity.class);
                startActivity(intent);
            });
        }
    }
}