package id.aguswmika.mahasiswadrawer.model;

import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("nim")
    private String nim;
    @SerializedName("nama")
    private String nama;
    @SerializedName("program_studi")
    private String program_studi;
    @SerializedName("alamat")
    private String alamat;

    public Mahasiswa(){}

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
}
