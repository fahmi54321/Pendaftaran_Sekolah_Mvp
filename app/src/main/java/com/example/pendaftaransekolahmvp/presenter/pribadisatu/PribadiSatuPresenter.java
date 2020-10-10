package com.example.pendaftaransekolahmvp.presenter.pribadisatu;

import android.util.Log;

import com.example.pendaftaransekolahmvp.model.java.ResponsePribadiSatu;
import com.example.pendaftaransekolahmvp.network.java.ApiService;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PribadiSatuPresenter {

    // todo 4 (pribadiSatu) membuat construct interface
    PribadiSatuView pribadiSatuView;

    public PribadiSatuPresenter(PribadiSatuView pribadiSatuView) {
        this.pribadiSatuView = pribadiSatuView;
    }

    // todo 3 (pribadiSatu) membuat fungsi create pribadi satu
    public void createPribadiSatu(RequestBody idUser,RequestBody namaPribadi, RequestBody jenisKelamin, RequestBody Nisn, MultipartBody.Part photoNisn,RequestBody Nik, MultipartBody.Part photoNik,RequestBody tempatLahir,RequestBody tglLahir)
    {
            Call<ResponsePribadiSatu> call = ApiService.Factory.create().tambahPribadiSatu(idUser,namaPribadi, jenisKelamin, Nisn, photoNisn, Nik, photoNik, tempatLahir, tglLahir);
            call.enqueue(new Callback<ResponsePribadiSatu>() {
                @Override
                public void onResponse(Call<ResponsePribadiSatu> call, Response<ResponsePribadiSatu> response) {

                    pribadiSatuView.onSuccess(response.message());
                    pribadiSatuView.hideLoading();
                    pribadiSatuView.hideProgres();
                    Log.d("Response Berhasil",response.message());

                }

                @Override
                public void onFailure(Call<ResponsePribadiSatu> call, Throwable t) {
                    pribadiSatuView.onError(t.getLocalizedMessage());
                    pribadiSatuView.hideLoading();
                    pribadiSatuView.hideProgres();
                    Log.d("Response Gagal 2",t.getLocalizedMessage());
                }
            });
    }
}
