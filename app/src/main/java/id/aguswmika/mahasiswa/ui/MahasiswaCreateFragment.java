package id.aguswmika.mahasiswa.ui;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import id.aguswmika.mahasiswa.R;
import id.aguswmika.mahasiswa.database.DatabaseHandler;
import id.aguswmika.mahasiswa.function.ApiClient;
import id.aguswmika.mahasiswa.function.ApiInterface;
import id.aguswmika.mahasiswa.model.Mahasiswa;
import id.aguswmika.mahasiswa.model.MahasiswaFormResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaCreateFragment extends Fragment {
    private Button submitBtn;
    private EditText namaTxt, nimTxt, alamatTxt;
    private Spinner programStudiTxt;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mahasiswa_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // buat api client;
        final ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);


        // ambil navigasi
        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        // ambil data dari form
        submitBtn = view.findViewById(R.id.submitBtn);
        namaTxt = view.findViewById(R.id.namaTxt);
        nimTxt = view.findViewById(R.id.nimTxt);
        alamatTxt = view.findViewById(R.id.alamatTxt);
        programStudiTxt = view.findViewById(R.id.programStudiTxt);

        // tambahkan listener ke button submit
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                int errors = 0;

                String nama, nim, alamat, programStudi;

                nama = namaTxt.getText().toString();
                nim = nimTxt.getText().toString();
                alamat = alamatTxt.getText().toString();
                programStudi = programStudiTxt.getSelectedItem().toString();

                if(TextUtils.isEmpty(nama)) {
                    errors += 1;
                    namaTxt.setError("Nama tidak boleh kosong");
                }

                if(TextUtils.isEmpty(nim)) {
                    errors += 1;
                    nimTxt.setError("NIM tidak boleh kosong");
                }

                if(TextUtils.isEmpty(alamat)) {
                    errors += 1;
                    alamatTxt.setError("Alamat tidak boleh kosong");
                }

                if(errors == 0) {
                    /*
                    DB SQLite
                    DatabaseHandler db = new DatabaseHandler(requireContext());

                    db.save(new Mahasiswa(
                        nim,
                        nama,
                        programStudi,
                        alamat
                    ));
                    */

                    Call<MahasiswaFormResult> createCall = apiClient.addMahasiswa(
                        nim,
                        nama,
                        programStudi,
                        alamat
                    );

                    createCall.enqueue(new Callback<MahasiswaFormResult>() {
                        @Override
                        public void onResponse(Call<MahasiswaFormResult> call, Response<MahasiswaFormResult> response) {
                            assert response.body() != null;
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.nav_mahasiswa_list);
                        }

                        @Override
                        public void onFailure(Call<MahasiswaFormResult> call, Throwable t) {
                            Log.e("Retrofit Get", t.toString());
                        }
                    });
                }
            }
        });
    }
}