package id.aguswmika.mahasiswadrawer.ui;

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

import id.aguswmika.mahasiswadrawer.R;
import id.aguswmika.mahasiswadrawer.model.Mahasiswa;
import id.aguswmika.mahasiswadrawer.viewModel.MahasiswaModel;

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
                TextView nama = view.findViewById(R.id.nama_text);
                nama.setText(mhs.getNama());

                TextView nim = view.findViewById(R.id.nim_text);
                nim.setText(mhs.getNim());

                TextView alamat = view.findViewById(R.id.alamat_text);
                alamat.setText(mhs.getAlamat());

                TextView program_studi = view.findViewById(R.id.program_studi_text);
                program_studi.setText(mhs.getProgramStudi());
            }
        });

    }
}