package com.example.pendaftaransekolahmvp.presenter.ayahkandung

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseAyahKandung
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (ayah kandung) konstruct interface
class AyahkandungPresenter(var ayahKandungView: AyahKandungView) {
    //todo 3 (ayah kandung) membuat fungsi tambahAyahKandung
    fun tambahAyahKandung(idUser:String,namaAyah:String,nikAyah:String,thnAyah:String,pddkAyah:String,pkjAyah:String,pbaAyah:String,bkhAyah:String)
    {
        if (idUser.isNotEmpty() && namaAyah.isNotEmpty() && nikAyah.isNotEmpty() && thnAyah.isNotEmpty() && pddkAyah.isNotEmpty() && pkjAyah.isNotEmpty() && bkhAyah.isNotEmpty())
        {
            //todo 4 (ayah kandung) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahAyahKandung(idUser,namaAyah,nikAyah,thnAyah,pddkAyah,pkjAyah,pbaAyah,bkhAyah).enqueue(object : Callback<ResponseAyahKandung>{
                override fun onFailure(call: Call<ResponseAyahKandung>, t: Throwable) {
                    ayahKandungView.onError(t.localizedMessage)
                    ayahKandungView.hideLoading()
                    ayahKandungView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponseAyahKandung>,
                    response: Response<ResponseAyahKandung>
                ) {
                    if (response.isSuccessful)
                    {
                        ayahKandungView.onSuccess(response.message())
                        ayahKandungView.hideLoading()
                        ayahKandungView.hideProgress()
                    }
                    else
                    {
                        ayahKandungView.onError(response.message())
                        ayahKandungView.hideLoading()
                        ayahKandungView.hideProgress()
                    }
                }

            })
        }
        else
        {
            ayahKandungView.inputKosong()
            ayahKandungView.hideLoading()
            ayahKandungView.hideProgress()
        }
    }
}