package com.example.pendaftaransekolahmvp.network.java;

import com.example.pendaftaransekolahmvp.model.java.ResponsePribadiSatu;
import com.example.pendaftaransekolahmvp.model.java.ResponseRegister;

import java.util.concurrent.TimeUnit;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


//todo 2.2 konfigurasi API
public interface ApiService {
    @Multipart
    @POST("signup.php")
    Call<ResponseRegister> signup
            (
                    @Part("nama_lengkap") RequestBody nama_lengkap,
                    @Part("nohp") RequestBody nohp,
                    @Part("alamat") RequestBody alamat,
                    @Part("email_address") RequestBody email_address,
                    @Part("password") RequestBody password,
                    @Part("confirm_password") RequestBody confirm_password,
                    @Part MultipartBody.Part photo
            );

    @Multipart
    @POST("tambahPribadiSatu.php")
    Call<ResponsePribadiSatu> tambahPribadiSatu
            (
                    @Part("id_user") RequestBody id_user,
                    @Part("nama_pribadi") RequestBody nama_pribadi,
                    @Part("jenis_kelamin") RequestBody jenis_kelamin,
                    @Part("nisn") RequestBody nisn,
                    @Part MultipartBody.Part foto_nisn,
                    @Part("nik") RequestBody nik,
                    @Part MultipartBody.Part foto_nik,
                    @Part("tempat_lahir") RequestBody tempat_lahir,
                    @Part("tgl_lahir") RequestBody tgl_lahir
            );

    class Factory {
        public static ApiService create() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);

        }
    }
}
