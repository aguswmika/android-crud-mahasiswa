package id.aguswmika.mahasiswadrawer.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.aguswmika.mahasiswadrawer.R;
import id.aguswmika.mahasiswadrawer.adapter.MahasiswaAdapter;
import id.aguswmika.mahasiswadrawer.function.ApiClient;
import id.aguswmika.mahasiswadrawer.function.ApiInterface;
import id.aguswmika.mahasiswadrawer.model.Mahasiswa;
import id.aguswmika.mahasiswadrawer.model.MahasiswaListResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaListFragment extends Fragment {

    Button mahasiswa_create_btn;
    ApiInterface apiInterface;
    RecyclerView mahasiswa_list;
    MahasiswaAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mahasiswa_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        mahasiswa_create_btn = (Button) view.findViewById(R.id.mahasiswa_create_btn);

        mahasiswa_create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            navController.navigate(R.id.nav_mahasiswa_create);
            }
        });

        mahasiswa_list = view.findViewById(R.id.mahasiswa_list);
        mahasiswa_list.setLayoutManager(new LinearLayoutManager(requireActivity()));

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<MahasiswaListResult> mahasiswaListCall = apiInterface.getMahasiswa();


        mahasiswaListCall.enqueue(new Callback<MahasiswaListResult>() {
            @Override
            public void onResponse(Call<MahasiswaListResult> call, Response<MahasiswaListResult> response) {
                assert response.body() != null;
                List<Mahasiswa> mahasiswaList = response.body().getData();
                Log.d("Retrofit Get", "Jumlah data Kontak: "+mahasiswaList.size());

                adapter = new MahasiswaAdapter(mahasiswaList);
                mahasiswa_list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MahasiswaListResult> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
