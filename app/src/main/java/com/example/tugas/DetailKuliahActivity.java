package com.example.tugas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tugas.R;

public class DetailKuliahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Pastikan layout ini isinya cuma FrameLayout dengan id fragment_container
        setContentView(R.layout.activity_detail_kuliah);

        // PENTING: Cek agar fragment tidak double saat layar diputar
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PertemuanListFragment())
                    .commit();
        }
    }
}