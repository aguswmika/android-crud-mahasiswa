package id.aguswmika.mahasiswadrawer.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import id.aguswmika.mahasiswadrawer.R;
import id.aguswmika.mahasiswadrawer.function.FileManage;
import id.aguswmika.mahasiswadrawer.function.SharedManage;
import id.aguswmika.mahasiswadrawer.model.Mahasiswa;
import id.aguswmika.mahasiswadrawer.model.MahasiswaList;
import id.aguswmika.mahasiswadrawer.viewModel.MahasiswaModel;

public class MahasiswaCreateFragment extends Fragment {
    private Button submit;
    private EditText nama, nim, alamat;
    private Spinner program_studi;
    private MahasiswaModel model;
    private FileManage file;
    private SharedManage share;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mahasiswa_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        file = new FileManage(requireContext());
        share = new SharedManage(requireContext());

        submit = view.findViewById(R.id.submit);
        nama = view.findViewById(R.id.nama);
        nim = view.findViewById(R.id.nim);
        alamat = view.findViewById(R.id.alamat);
        program_studi = view.findViewById(R.id.program_studi);

        model = new ViewModelProvider(requireActivity()).get(MahasiswaModel.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                int errors = 0;


                if(TextUtils.isEmpty(nama.getText())) {
                    errors += 1;
                    nama.setError("Nama tidak boleh kosong");
                }

                if(TextUtils.isEmpty(nim.getText())) {
                    errors += 1;
                    nim.setError("NIM tidak boleh kosong");
                }

                if(TextUtils.isEmpty(alamat.getText())) {
                    errors += 1;
                    alamat.setError("Alamat tidak boleh kosong");
                }

                if(errors == 0) {
//                    List<String> files = Arrays.asList(getContext().fileList());
                    Gson gson = new Gson();

                    try {
                        String listMahasiswa = file.read();
                        String listMahasiswaPref = share.read("mahasiswa");

                        MahasiswaList mahasiswas = gson.fromJson(listMahasiswa, MahasiswaList.class);
                        MahasiswaList mahasiswasPref = gson.fromJson(listMahasiswaPref, MahasiswaList.class);

                        if(mahasiswas == null){
                            mahasiswas = new MahasiswaList();
                        }

                        if(mahasiswasPref == null){
                            mahasiswasPref = new MahasiswaList();
                        }

                        mahasiswas.addMahasiswa(new Mahasiswa(
                            nim.getText().toString(),
                            nama.getText().toString(),
                            alamat.getText().toString(),
                            program_studi.getSelectedItem().toString()
                        ));

                        mahasiswasPref.addMahasiswa(new Mahasiswa(
                                nim.getText().toString(),
                                nama.getText().toString(),
                                alamat.getText().toString(),
                                program_studi.getSelectedItem().toString()
                        ));


                        file.write(gson.toJson(mahasiswas));

                        share.write("mahasiswa", gson.toJson(mahasiswasPref));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

//                    Log.d("hasol", gson.toJson(new Mahasiswa(
//                            nim.getText().toString(),
//                            nama.getText().toString(),
//                            alamat.getText().toString(),
//                            program_studi.getSelectedItem().toString()
//                    )));

//                    model.add(new Mahasiswa(nim.getText().toString(), nama.getText().toString(), alamat.getText().toString(), program_studi.getSelectedItem().toString()));

                    Toast.makeText(getContext(), "Berhasil menyimpan " + nama.getText(), Toast.LENGTH_SHORT).show();

                    navController.navigate(R.id.nav_mahasiswa_list);
                }
            }
        });
    }
}