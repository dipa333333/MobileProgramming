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

    private TextView tvNamaMatkul, tvKodeMatkul; // Hanya sisakan ini
    private Button btnEdit, btnHapus;
    private int idTugas;

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

        tvNamaMatkul = findViewById(R.id.tv_detail_nama_matkul);
        tvKodeMatkul = findViewById(R.id.tv_detail_kode_matkul);
        btnEdit = findViewById(R.id.btn_edit_tugas);
        btnHapus = findViewById(R.id.btn_hapus_tugas);

        idTugas = getIntent().getIntExtra("ID_TUGAS", -1);

        if (idTugas != -1) {
            tampilkanDetailTugas();
        }

        btnHapus.setOnClickListener(v -> {
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String sql = "DELETE FROM matkul WHERE id = ?";
            db.execSQL(sql, new Object[]{idTugas});
            Toast.makeText(this, "Mata Kuliah berhasil dihapus!", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(DetailItemKuliah.this, EditTugasActivity.class);
            intent.putExtra("ID", idTugas);
            intent.putExtra("KODE", tvKodeMatkul.getText().toString());
            intent.putExtra("NAMA", tvNamaMatkul.getText().toString());
            startActivity(intent);
        });
    }

    private void tampilkanDetailTugas() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query tanpa tugas & deadline
        String sql = "SELECT kode_matkul, nama_matkul FROM matkul WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(idTugas)});

        if (c.moveToFirst()) {
            tvKodeMatkul.setText(c.getString(0));
            tvNamaMatkul.setText(c.getString(1));
        }
        c.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (idTugas != -1) {
            tampilkanDetailTugas();
        }
    }
}