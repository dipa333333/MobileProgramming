package com.example.tugas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class PertemuanListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pertemuan_list, container, false);

        // --- 1. INISIALISASI WADAH PREVIEW ---
        LinearLayout layoutPreviewBaris1 = view.findViewById(R.id.layout_preview_baris_1);
        TextView tvPreviewTitleBaris1 = view.findViewById(R.id.tvPreviewTitleBaris1);
        LinearLayout btnKeDetail1 = view.findViewById(R.id.btnKeHalamanMateri1);

        LinearLayout layoutPreviewBaris2 = view.findViewById(R.id.layout_preview_baris_2);
        TextView tvPreviewTitleBaris2 = view.findViewById(R.id.tvPreviewTitleBaris2);
        LinearLayout btnKeDetail2 = view.findViewById(R.id.btnKeHalamanMateri2);

        LinearLayout layoutPreviewBaris3 = view.findViewById(R.id.layout_preview_baris_3);
        TextView tvPreviewTitleBaris3 = view.findViewById(R.id.tvPreviewTitleBaris3);
        LinearLayout btnKeDetail3 = view.findViewById(R.id.btnKeHalamanMateri3);

        // --- 2. INISIALISASI TOMBOL PERTEMUAN ---
        LinearLayout btn1 = view.findViewById(R.id.btnPertemuan1);
        LinearLayout btn2 = view.findViewById(R.id.btnPertemuan2);
        LinearLayout btn3 = view.findViewById(R.id.btnPertemuan3);
        LinearLayout btn4 = view.findViewById(R.id.btnPertemuan4);
        LinearLayout btn5 = view.findViewById(R.id.btnPertemuan5);
        LinearLayout btn6 = view.findViewById(R.id.btnPertemuan6);

        // --- 3. LOGIKA KLIK TOMBOL ---
        btn1.setOnClickListener(v -> togglePreview(layoutPreviewBaris1, tvPreviewTitleBaris1, "PERTEMUAN 1", layoutPreviewBaris2, layoutPreviewBaris3));
        btn2.setOnClickListener(v -> togglePreview(layoutPreviewBaris1, tvPreviewTitleBaris1, "PERTEMUAN 2", layoutPreviewBaris2, layoutPreviewBaris3));

        btn3.setOnClickListener(v -> togglePreview(layoutPreviewBaris2, tvPreviewTitleBaris2, "PERTEMUAN 3", layoutPreviewBaris1, layoutPreviewBaris3));
        btn4.setOnClickListener(v -> togglePreview(layoutPreviewBaris2, tvPreviewTitleBaris2, "PERTEMUAN 4", layoutPreviewBaris1, layoutPreviewBaris3));

        btn5.setOnClickListener(v -> togglePreview(layoutPreviewBaris3, tvPreviewTitleBaris3, "PERTEMUAN 5", layoutPreviewBaris1, layoutPreviewBaris2));
        btn6.setOnClickListener(v -> togglePreview(layoutPreviewBaris3, tvPreviewTitleBaris3, "PERTEMUAN 6", layoutPreviewBaris1, layoutPreviewBaris2));

        // --- 4. LOGIKA TOMBOL DETAIL ---
        btnKeDetail1.setOnClickListener(v -> {
            String judul = tvPreviewTitleBaris1.getText().toString();
            if (judul.equals("PERTEMUAN 1")) bukaDetail("1", "Dasar Ekonomi");
            else bukaDetail("2", "Analisis Pasar");
        });

        btnKeDetail2.setOnClickListener(v -> {
            String judul = tvPreviewTitleBaris2.getText().toString();
            if (judul.equals("PERTEMUAN 3")) bukaDetail("3", "Manajemen SDM");
            else bukaDetail("4", "Strategi Bisnis");
        });

        btnKeDetail3.setOnClickListener(v -> {
            String judul = tvPreviewTitleBaris3.getText().toString();
            if (judul.equals("PERTEMUAN 5")) bukaDetail("5", "Digital Marketing");
            else bukaDetail("6", "Evaluasi Akhir");
        });

        return view;
    }

    private void togglePreview(LinearLayout target, TextView tvTitle, String text, LinearLayout other1, LinearLayout other2) {
        other1.setVisibility(View.GONE);
        other2.setVisibility(View.GONE);

        if (target.getVisibility() == View.VISIBLE && tvTitle.getText().toString().equals(text)) {
            target.setVisibility(View.GONE);
        } else {
            tvTitle.setText(text);
            target.setVisibility(View.VISIBLE);
        }
    }

    private void bukaDetail(String nomor, String materi) {
        PertemuanDetailFragment detailFragment = new PertemuanDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NOMOR", nomor);
        bundle.putString("MATERI", materi);
        detailFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }
}