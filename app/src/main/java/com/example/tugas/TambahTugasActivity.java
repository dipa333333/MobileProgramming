package com.example.tugas;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TambahTugasActivity extends AppCompatActivity {

    private EditText etKode, etNama;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambah_tugas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Inisialisasi komponen form (Hanya Kode dan Nama)
        etKode = findViewById(R.id.et_kode_matkul);
        etNama = findViewById(R.id.et_nama_matkul);
        btnSimpan = findViewById(R.id.btn_simpan);

        // 2. Aksi saat tombol Simpan diklik
        btnSimpan.setOnClickListener(v -> {
            // Ambil teks dari inputan user
            String kode = etKode.getText().toString();
            String nama = etNama.getText().toString();

            // Cek apakah ada kolom yang kosong
            if (kode.isEmpty() || nama.isEmpty()) {
                Toast.makeText(this, "Kode dan Nama Matkul wajib diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            // 3. Proses INSERT ke Database SQLite
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Insert HANYA 2 data: kode_matkul dan nama_matkul
            String sql = "INSERT INTO matkul (kode_matkul, nama_matkul) VALUES (?, ?)";
            db.execSQL(sql, new Object[]{kode, nama});

            Toast.makeText(this, "Mata Kuliah berhasil disimpan!", Toast.LENGTH_SHORT).show();

            // 4. Tutup halaman form dan kembali ke halaman sebelumnya (MainActivity)
            finish();
        });
    }
}