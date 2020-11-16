package id.aguswmika.mahasiswadrawer.function;

import id.aguswmika.mahasiswadrawer.model.MahasiswaFormResult;
import id.aguswmika.mahasiswadrawer.model.MahasiswaListResult;
import id.aguswmika.mahasiswadrawer.model.MahasiswaSingleResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {
    @GET("/mahasiswa.php")
    Call<MahasiswaListResult> getMahasiswa();

    @Headers({"Content-Type: application/json"})
    @GET("/mahasiswa.php")
    Call<MahasiswaSingleResult> getMahasiswaDetail(@Field("nim") String nim);

    @FormUrlEncoded
    @POST("/mahasiswa.php")
    Call<MahasiswaFormResult> addMahasiswa(@Field("nim") String nim,
                                           @Field("nama") String nama,
                                           @Field("program_studi") String program_studi,
                                           @Field("alamat") String alamat);
    @FormUrlEncoded
    @PUT("/mahasiswa.php")
    Call<MahasiswaFormResult> editMahasiswa(@Field("nim") String nim,
                                            @Field("nama") String nama,
                                            @Field("program_studi") String program_studi,
                                            @Field("alamat") String alamat);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "mahasiswa", hasBody = true)
    Call<MahasiswaFormResult> deleteMahasiswa(@Field("nim") String nim);
}