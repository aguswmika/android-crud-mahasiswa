package id.aguswmika.mahasiswadrawer.model;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaList {
    private List<Mahasiswa> mahasiswas;

    public MahasiswaList(){
        mahasiswas = new ArrayList<>();
    }

    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }

    public void addMahasiswa(Mahasiswa mahasiswa){
        mahasiswas.add(mahasiswa);
    }

}
