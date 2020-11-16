package id.aguswmika.mahasiswadrawer.model;

import com.google.gson.annotations.SerializedName;

public class MahasiswaSingleResult {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private Mahasiswa data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Mahasiswa getData() {
        return data;
    }

    public void setData(Mahasiswa data) {
        this.data = data;
    }
}
