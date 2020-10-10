package com.example.pendaftaransekolahmvp.presenter.pribadiempat

import android.util.Log
import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiEmpat
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (pribadiEmpat) construct interface
class PribadiEmpatPresenter(var pribadiEmpatView: PribadiEmpatView) {

    //todo 3 (pribadiEmpat) membuat fungsi tambahPribadiEmpat
    fun tambahPribadiEmpat(idUser:String,tempatTinggal:String,modeTransportasi:String,noKK:String,anakKe:String,penerimaKps:String,noKps:String)
    {
        if (tempatTinggal.isNotEmpty() && modeTransportasi.isNotEmpty() && noKK.isNotEmpty() && anakKe.isNotEmpty() && penerimaKps.isNotEmpty())
        {
            //todo 4 (pribadiEmpat) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahPribadiEmpat(idUser,tempatTinggal,modeTransportasi,noKK,anakKe,penerimaKps,noKps).enqueue(object : Callback<ResponsePribadiEmpat>{
                override fun onFailure(call: Call<ResponsePribadiEmpat>, t: Throwable) {
                    Log.d("Response Gagal Total",t.localizedMessage)
                    pribadiEmpatView.onError(t.localizedMessage)
                    pribadiEmpatView.hideLoading()
                    pribadiEmpatView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponsePribadiEmpat>,
                    response: Response<ResponsePribadiEmpat>
                ) {
                    if (response.isSuccessful)
                    {
                        Log.d("Response Berhasil",response.message())
                        pribadiEmpatView.onSuccess(response.message())
                        pribadiEmpatView.hideLoading()
                        pribadiEmpatView.hideProgress()
                    }
                    else
                    {
                        Log.d("Response Gagal",response.message())
                        pribadiEmpatView.onError(response.message())
                        pribadiEmpatView.hideLoading()
                        pribadiEmpatView.hideProgress()
                    }
                }

            })
        }
        else
        {
            pribadiEmpatView.inputKosong()
            pribadiEmpatView.hideLoading()
            pribadiEmpatView.hideProgress()
        }
    }
}