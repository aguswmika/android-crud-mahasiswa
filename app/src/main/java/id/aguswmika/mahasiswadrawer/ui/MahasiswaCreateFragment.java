package id.aguswmika.mahasiswadrawer.ui;

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

import com.google.gson.Gson;

import java.io.FileNotFoundException;

import id.aguswmika.mahasiswadrawer.R;
import id.aguswmika.mahasiswadrawer.function.ApiClient;
import id.aguswmika.mahasiswadrawer.function.ApiInterface;
import id.aguswmika.mahasiswadrawer.model.MahasiswaFormResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaCreateFragment extends Fragment {
    private Button submit;
    private EditText nama, nim, alamat;
    private Spinner program_studi;
    private ApiInterface apiInterface;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mahasiswa_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        submit = view.findViewById(R.id.submit);
        nama = view.findViewById(R.id.nama);
        nim = view.findViewById(R.id.nim);
        alamat = view.findViewById(R.id.alamat);
        program_studi = view.findViewById(R.id.program_studi);



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
                    Call<MahasiswaFormResult> createCall = apiInterface.addMahasiswa(nim.getText().toString(), nama.getText().toString(), program_studi.getSelectedItem().toString(), alamat.getText().toString());
                    createCall.enqueue(new Callback<MahasiswaFormResult>() {
                        @Override
                        public void onResponse(Call<MahasiswaFormResult> call, Response<MahasiswaFormResult> response) {
                            assert response.body() != null;
                            Log.e("e", response.body().getMessage());
                            Toast.makeText(getContext(), "Berhasil menyimpan " + response.body().getMessage(), Toast.LENGTH_SHORT).show();

//                            navController.navigate(R.id.nav_mahasiswa_list);
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