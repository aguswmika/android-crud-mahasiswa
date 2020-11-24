package id.aguswmika.mahasiswa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import id.aguswmika.mahasiswa.R;
import id.aguswmika.mahasiswa.model.Mahasiswa;
import id.aguswmika.mahasiswa.viewModel.MahasiswaModel;

public class AboutFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MahasiswaModel model = new ViewModelProvider(requireActivity()).get(MahasiswaModel.class);
        model.get().observe(getViewLifecycleOwner(), new Observer<Mahasiswa>() {
            @Override
            public void onChanged(Mahasiswa mhs) {
            TextView nama = view.findViewById(R.id.namaLbl);
            nama.setText(mhs.getNama());

            TextView nim = view.findViewById(R.id.nimLbl);
            nim.setText(mhs.getNim());

            TextView alamat = view.findViewById(R.id.alamatTxt);
            alamat.setText(mhs.getAlamat());

            TextView program_studi = view.findViewById(R.id.programStudiTxt);
            program_studi.setText(mhs.getProgramStudi());
            }
        });

    }
}