package id.aguswmika.mahasiswa.ui;

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

import java.util.List;
import java.util.Objects;

import id.aguswmika.mahasiswa.R;
import id.aguswmika.mahasiswa.adapter.MahasiswaAdapter;
import id.aguswmika.mahasiswa.database.DatabaseHandler;
import id.aguswmika.mahasiswa.function.ApiClient;
import id.aguswmika.mahasiswa.function.ApiInterface;
import id.aguswmika.mahasiswa.model.Mahasiswa;
import id.aguswmika.mahasiswa.model.MahasiswaListResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaListFragment extends Fragment {

    Button createBtn;
    ApiInterface apiInterface;
    RecyclerView recycler;
    MahasiswaAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mahasiswa_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        createBtn = (Button) view.findViewById(R.id.createBtn);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            navController.navigate(R.id.nav_mahasiswa_create);
            }
        });

        recycler = view.findViewById(R.id.mahasiswaRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));

        /*
        DatabaseHandler db = new DatabaseHandler(requireContext());

        List<Mahasiswa> listMahasiswa = db.findAll();
         */

        Call<MahasiswaListResult> getCall = apiClient.getMahasiswa();

        getCall.enqueue(new Callback<MahasiswaListResult>() {
            @Override
            public void onResponse(Call<MahasiswaListResult> call, Response<MahasiswaListResult> response) {
                assert response.body() != null;
                adapter = new MahasiswaAdapter(response.body().getData());
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MahasiswaListResult> call, Throwable t) {
                Log.e("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
