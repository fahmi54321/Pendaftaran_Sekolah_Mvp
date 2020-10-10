package com.example.pendaftaransekolahmvp.presenter.wali

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseWali
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (wali) konstruct view
class WaliPresenter(var waliView: WaliView) {
    //todo 3 (wali) membuat fungsi tambahWali
    fun tambahWali(idUser:String,namaWali:String,nikWali:String,thnWali:String,pddkWali:String,pkjWali:String,pbaWali:String)
    {
        if (namaWali.isNotEmpty() && nikWali.isNotEmpty() && thnWali.isNotEmpty() && pddkWali.isNotEmpty() && pkjWali.isNotEmpty() && pbaWali.isNotEmpty())
        {
            //todo 4 (wali) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahWali(idUser,namaWali,nikWali,thnWali,pddkWali,pkjWali,pbaWali).enqueue(object : Callback<ResponseWali>{
                override fun onFailure(call: Call<ResponseWali>, t: Throwable) {
                    waliView.onError(t.localizedMessage)
                    waliView.hideLoading()
                    waliView.hideProgress()
                }

                override fun onResponse(call: Call<ResponseWali>, response: Response<ResponseWali>) {
                    if (response.isSuccessful)
                    {
                        waliView.onSuccess(response.message())
                        waliView.hideLoading()
                        waliView.hideProgress()
                    }
                    else
                    {
                        waliView.onError(response.message())
                        waliView.hideLoading()
                        waliView.hideProgress()
                    }
                }

            })
        }
        else
        {
            waliView.inputKosong()
            waliView.hideLoading()
            waliView.hideProgress()
        }
    }
}