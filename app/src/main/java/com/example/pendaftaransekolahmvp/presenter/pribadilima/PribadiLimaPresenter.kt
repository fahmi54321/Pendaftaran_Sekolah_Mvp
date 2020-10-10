package com.example.pendaftaransekolahmvp.presenter.pribadilima

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiLima
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 5 (pribadiLima) konstruct interface
class PribadiLimaPresenter(var pribadiLimaView: PribadiLimaView) {

    // todo 3 (pribadiLima) membuat fungsi tambahPribadiLima
    fun tambahPribadiLima(idUser:String,usulanSekolah:String,penerimaKip:String,noKip:String,namaKip:String,terimaFisikKip:String,alasanTerimaKip:String,bank:String,noRekBank:String,namaRek:String)
    {
        // todo 4 (pribadiLima) konfigurasi retrofit
        ConfigNetwork.getRetrofit().tambahPribadiLima(idUser,usulanSekolah,penerimaKip,noKip,namaKip,terimaFisikKip,alasanTerimaKip,bank,noRekBank,namaRek).enqueue(object : Callback<ResponsePribadiLima>{
            override fun onFailure(call: Call<ResponsePribadiLima>, t: Throwable) {
                pribadiLimaView.onError(t.localizedMessage)
                pribadiLimaView.hideLoading()
                pribadiLimaView.hideProgress()
            }

            override fun onResponse(
                call: Call<ResponsePribadiLima>,
                response: Response<ResponsePribadiLima>
            ) {
                if (response.isSuccessful)
                {
                    pribadiLimaView.onSuccess(response.message())
                    pribadiLimaView.hideLoading()
                    pribadiLimaView.hideProgress()
                }
                else
                {
                    pribadiLimaView.onError(response.message())
                    pribadiLimaView.hideLoading()
                    pribadiLimaView.hideProgress()
                }
            }

        })
    }
}