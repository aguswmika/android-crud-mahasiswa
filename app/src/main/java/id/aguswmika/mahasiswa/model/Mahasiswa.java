package id.aguswmika.mahasiswa.model;

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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) { this.nim = nim; }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getProgramStudi() {
        return program_studi;
    }

    public void setProgramStudi(String program_studi) {
        this.program_studi = program_studi;
    }
}
