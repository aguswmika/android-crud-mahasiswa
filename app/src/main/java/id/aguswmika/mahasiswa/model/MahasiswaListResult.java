package id.aguswmika.mahasiswa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MahasiswaListResult {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Mahasiswa> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Mahasiswa> getData() {
        return data;
    }

    public void setData(List<Mahasiswa> data) {
        this.data = data;
    }

}
