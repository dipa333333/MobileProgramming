package com.example.tugas;

import android.content.Intent; // Jangan lupa import Intent!
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView; // Jangan lupa import TextView!

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // 1. Logika untuk padding layar (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. INISIALISASI KOMPONEN (Harus di dalam onCreate)
        TextView lupa = findViewById(R.id.lupa);

        // 3. LOGIKA CLICK LISTENER
        lupa.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // 4. Button Masuk
        Button btnMasuk = findViewById(R.id.btnMasuk);

        btnMasuk.setOnClickListener(v -> {
            // 3. Buat "Tiket Perjalanan" (Intent) dari Login ke Detail Kuliah
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);

            // (Opsional) Tutup halaman login agar user gak bisa balik lagi pakai tombol back
            // finish();
        });
    } // Akhir dari onCreate
} // Akhir dari class