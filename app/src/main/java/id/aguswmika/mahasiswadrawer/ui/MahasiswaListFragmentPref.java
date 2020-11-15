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

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import id.aguswmika.mahasiswadrawer.R;
import id.aguswmika.mahasiswadrawer.adapter.MahasiswaAdapter;
import id.aguswmika.mahasiswadrawer.function.FileManage;
import id.aguswmika.mahasiswadrawer.function.SharedManage;
import id.aguswmika.mahasiswadrawer.model.Mahasiswa;
import id.aguswmika.mahasiswadrawer.model.MahasiswaList;

public class MahasiswaListFragmentPref extends Fragment {

    ArrayList<Mahasiswa> mahasiswas;
    Button mahasiswa_create_btn;
    SharedManage share;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mahasiswa_list_pref, container, false);
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

        // Lookup the recyclerview in activity layout
        RecyclerView mahasiswa_list = (RecyclerView) view.findViewById(R.id.mahasiswa_list);

        // Initialize contacts
//        mahasiswas = Mahasiswa.createMahasiswaList(20);
        share = new SharedManage(requireContext());
        Gson gson = new Gson();


        String listMahasiswa = share.read("mahasiswa");
        MahasiswaList mahasiswas = gson.fromJson(listMahasiswa, MahasiswaList.class);

        if (mahasiswas == null) {
            mahasiswas = new MahasiswaList();
        }
        // Create adapter passing in the sample user data
        MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswas.getMahasiswas());
        // Attach the adapter to the recyclerview to populate items
        mahasiswa_list.setAdapter(adapter);

        // Set layout manager to position the items
        mahasiswa_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        // That's all!
    }
}
