package com.example.tugas;
import com.example.tugas.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class PertemuanDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pertemuan_detail, container, false);

        TextView tvTitle = view.findViewById(R.id.tvDetailTitle);
        TextView tvMateri = view.findViewById(R.id.tvMateriName);
        TextView tvTugas = view.findViewById(R.id.tvTugasDesc);

        if (getArguments() != null) {
            String nomor = getArguments().getString("NOMOR");
            String materi = getArguments().getString("MATERI");

            tvTitle.setText("PERTEMUAN " + nomor);
            tvMateri.setText("Materi Pertemuan " + nomor + "\n" + materi);
            tvTugas.setText("Tugas membuat rangkuman mengenai " + materi);
        }

        return view;
    }
}