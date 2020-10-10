package com.example.pendaftaransekolahmvp.presenter.pribaditiga

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiTiga
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (pribadiTiga) konstruct interface
class PribadiTigaPresenter(var pribadiTigaView: PribadiTigaView) {

    //todo 3 (pribadiTiga) membuat fungsi tambahPribadiTiga
    fun tambahPribadiTiga(idUser:String,namaDusun:String,namaKelurahan:String,kecamatan:String,kodePos:String,lintang:String,bujur:String)
    {
        if (idUser.isNotEmpty() && namaDusun.isNotEmpty() && namaKelurahan.isNotEmpty() && kecamatan.isNotEmpty() && kodePos.isNotEmpty() && lintang.isNotEmpty() && bujur.isNotEmpty())
        {
            //todo 4 (pribadiTiga) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahPribadiTiga(idUser,namaDusun,namaKelurahan,kecamatan,kodePos,lintang,bujur).enqueue(object : Callback<ResponsePribadiTiga>{
                override fun onFailure(call: Call<ResponsePribadiTiga>, t: Throwable) {
                    pribadiTigaView.onError(t.localizedMessage)
                    pribadiTigaView.hideLoading()
                    pribadiTigaView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponsePribadiTiga>,
                    response: Response<ResponsePribadiTiga>
                ) {
                    if (response.isSuccessful)
                    {
                        pribadiTigaView.onSuccess(response.message())
                        pribadiTigaView.hideLoading()
                        pribadiTigaView.hideProgress()
                    }
                    else
                    {
                        pribadiTigaView.onError(response.message())
                        pribadiTigaView.hideLoading()
                        pribadiTigaView.hideProgress()
                    }
                }

            })
        }
        else
        {
            pribadiTigaView.inputKosong()
            pribadiTigaView.hideLoading()
            pribadiTigaView.hideProgress()
        }
    }
}