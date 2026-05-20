package com.example.tugas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailItemKuliah extends AppCompatActivity {

    private TextView tvNamaMatkul, tvDeskripsiTugas, tvKodeMatkul, tvDeadline;
    private Button btnEdit, btnHapus;
    private int idTugas; // Untuk menyimpan ID tugas yang sedang dibuka

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_item_kuliah);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Inisialisasi Komponen View
        tvNamaMatkul = findViewById(R.id.tv_detail_nama_matkul);
        tvDeskripsiTugas = findViewById(R.id.tv_detail_deskripsi_tugas);
        tvKodeMatkul = findViewById(R.id.tv_detail_kode_matkul);
        tvDeadline = findViewById(R.id.tv_detail_deadline);
        btnEdit = findViewById(R.id.btn_edit_tugas);
        btnHapus = findViewById(R.id.btn_hapus_tugas);

        // 2. Tangkap ID Tugas yang dikirim dari MainActivity
        idTugas = getIntent().getIntExtra("ID_TUGAS", -1);

        if (idTugas != -1) {
            tampilkanDetailTugas();
        }

        // 3. Logika Tombol Hapus (DELETE)
        btnHapus.setOnClickListener(v -> {
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Jalankan query DELETE berdasarkan ID
            String sql = "DELETE FROM matkul WHERE id = ?";
            db.execSQL(sql, new Object[]{idTugas});

            Toast.makeText(this, "Tugas berhasil dihapus!", Toast.LENGTH_SHORT).show();

            // Tutup halaman detail dan otomatis kembali ke MainActivity
            finish();
        });

        // 4. Logika Tombol Edit (UPDATE)
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(DetailItemKuliah.this, EditTugasActivity.class);
            // Kirim data saat ini ke halaman Edit
            intent.putExtra("ID", idTugas);
            intent.putExtra("KODE", tvKodeMatkul.getText().toString());
            intent.putExtra("NAMA", tvNamaMatkul.getText().toString());
            intent.putExtra("TUGAS", tvDeskripsiTugas.getText().toString());
            intent.putExtra("DEADLINE", tvDeadline.getText().toString());
            startActivity(intent);
        });
    }

    private void tampilkanDetailTugas() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Ambil data spesifik sesuai ID tugas menggunakan klausa WHERE
        String sql = "SELECT kode_matkul, nama_matkul, tugas, deadline FROM matkul WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(idTugas)});

        if (c.moveToFirst()) {
            String kode = c.getString(0);
            String nama = c.getString(1);
            String tugas = c.getString(2);
            String deadline = c.getString(3);

            // Set data dari database ke TextView layar
            tvKodeMatkul.setText(kode);
            tvNamaMatkul.setText(nama);
            tvDeskripsiTugas.setText(tugas);
            tvDeadline.setText(deadline);
        }
        c.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (idTugas != -1) {
            tampilkanDetailTugas(); // Refresh data dari database saat kembali dari form edit
        }
    }
}