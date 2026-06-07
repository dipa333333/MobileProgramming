package com.example.tugas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnTambah;
    private ListView lv;
    private String[] dataMatkul;
    private int[] idMatkul;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.lv);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TambahTugasActivity.class);
            startActivity(intent);
        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, DetailItemKuliah.class);
            intent.putExtra("ID_TUGAS", idMatkul[position]);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilkanData();
    }

    private void tampilkanData() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // SELECT HANYA ID, KODE, DAN NAMA
        String sql = "SELECT id, kode_matkul, nama_matkul FROM matkul";
        Cursor c = db.rawQuery(sql, null);

        dataMatkul = new String[c.getCount()];
        idMatkul = new int[c.getCount()];

        for (int i = 0; i < c.getCount(); i++) {
            c.moveToPosition(i);
            idMatkul[i] = c.getInt(0);

            String kode = c.getString(1);
            String nama = c.getString(2);

            // Format tampilan di list tanpa tugas & deadline
            dataMatkul[i] = kode + " - " + nama;
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataMatkul);
        lv.setAdapter(adapter);
    }
}