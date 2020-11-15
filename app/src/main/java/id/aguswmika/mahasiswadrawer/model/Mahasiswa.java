package id.aguswmika.mahasiswadrawer.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Mahasiswa {
    String nim, nama, program_studi, alamat;
    public Mahasiswa(String nim, String nama, String program_studi, String alamat){
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.program_studi = program_studi;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNim() {
        return nim;
    }

    public String getProgramStudi() {
        return program_studi;
    }


//    private static int lastMahasiswaNim = 1;

    public static ArrayList<Mahasiswa> createMahasiswaList(int numContacts) {
        ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
        ArrayList<String> namaMahasiswa = new ArrayList<>();

        namaMahasiswa.add("Agus");
        namaMahasiswa.add("Made");
        namaMahasiswa.add("Nyoman");
        namaMahasiswa.add("Ketut");
        namaMahasiswa.add("Budi");
        namaMahasiswa.add("Kenny");
        namaMahasiswa.add("Putu");
        namaMahasiswa.add("Uranus");
        namaMahasiswa.add("Zilong");

        for (int i = 1; i <= numContacts; i++) {
            mahasiswas.add(new Mahasiswa("170856100"+String.valueOf(i), namaMahasiswa.get(new Random().nextInt(namaMahasiswa.size()))+' '+namaMahasiswa.get(new Random().nextInt(namaMahasiswa.size())), "Teknik Informatika", "Jimbaran"));
//            Log.d("apem", String.valueOf());
//            lastMahasiswaNim++;
        }

        return mahasiswas;
    }
}
