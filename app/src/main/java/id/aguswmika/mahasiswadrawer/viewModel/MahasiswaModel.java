package id.aguswmika.mahasiswadrawer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.aguswmika.mahasiswadrawer.model.Mahasiswa;

public class MahasiswaModel extends ViewModel {
    private final MutableLiveData<Mahasiswa> mahasiswa = new MutableLiveData<Mahasiswa>();

    public void add(Mahasiswa mhs){
        mahasiswa.setValue(mhs);
    }

    public LiveData<Mahasiswa> get(){
        return mahasiswa;
    }
}
