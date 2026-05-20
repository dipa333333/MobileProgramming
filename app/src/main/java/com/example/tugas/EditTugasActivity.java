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

public class EditTugasActivity extends AppCompatActivity {

    private EditText etKode, etNama, etTugas, etDeadline;
    private Button btnSimpanPerubahan;
    private int idTugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_tugas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Inisialisasi
        etKode = findViewById(R.id.et_edit_kode);
        etNama = findViewById(R.id.et_edit_nama);
        etTugas = findViewById(R.id.et_edit_tugas);
        etDeadline = findViewById(R.id.et_edit_deadline);
        btnSimpanPerubahan = findViewById(R.id.btn_simpan_perubahan);

        // 2. Tangkap data lama dari Intent dan masukkan ke EditText
        idTugas = getIntent().getIntExtra("ID", -1);
        etKode.setText(getIntent().getStringExtra("KODE"));
        etNama.setText(getIntent().getStringExtra("NAMA"));
        etTugas.setText(getIntent().getStringExtra("TUGAS"));
        etDeadline.setText(getIntent().getStringExtra("DEADLINE"));

        // 3. Aksi ketika tombol Simpan Perubahan diklik
        btnSimpanPerubahan.setOnClickListener(v -> {
            String kodeBaru = etKode.getText().toString();
            String namaBaru = etNama.getText().toString();
            String tugasBaru = etTugas.getText().toString();
            String deadlineBaru = etDeadline.getText().toString();

            if (kodeBaru.isEmpty() || namaBaru.isEmpty()) {
                Toast.makeText(this, "Kode dan Nama Matkul wajib diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Jalankan perintah UPDATE ke database
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String sql = "UPDATE matkul SET kode_matkul = ?, nama_matkul = ?, tugas = ?, deadline = ? WHERE id = ?";
            db.execSQL(sql, new Object[]{kodeBaru, namaBaru, tugasBaru, deadlineBaru, idTugas});

            Toast.makeText(this, "Tugas berhasil diubah!", Toast.LENGTH_SHORT).show();
            finish(); // Tutup halaman edit
        });
    }
}