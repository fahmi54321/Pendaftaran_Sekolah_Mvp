package com.example.pendaftaransekolahmvp.presenter.dataperiodik

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseDataPeriodik
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (dataPeriodik) membuat construct interface
class DataPeriodikPresenter(var dataPeriodikView: DataPeriodikView) {

    //todo 3 (dataPeriodik) membuat fungsi tambahDataPeriodik
    fun tambahDataPeriodik(idUser:String,tggiBadan:String,beratBadan:String,jarakSekolah:String,km:String,waktuTempuhJam:String,waktuTempuhMenit:String,waktuTempuhSekolah:String,jmlSaudarakandung:String)
    {
        if (idUser.isNotEmpty() && tggiBadan.isNotEmpty() && beratBadan.isNotEmpty() && jarakSekolah.isNotEmpty() && km.isNotEmpty() && waktuTempuhJam.isNotEmpty() && waktuTempuhMenit.isNotEmpty() && jmlSaudarakandung.isNotEmpty())
        {
            //todo 5 (dataPeriodik) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahDataPeriodik(idUser,tggiBadan,beratBadan,jarakSekolah,km,waktuTempuhJam,waktuTempuhMenit,waktuTempuhSekolah,jmlSaudarakandung).enqueue(object : Callback<ResponseDataPeriodik>{
                override fun onFailure(call: Call<ResponseDataPeriodik>, t: Throwable) {
                    dataPeriodikView.onError(t.localizedMessage)
                    dataPeriodikView.hideLoading()
                    dataPeriodikView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponseDataPeriodik>,
                    response: Response<ResponseDataPeriodik>
                ) {
                    if (response.isSuccessful)
                    {
                        dataPeriodikView.onSuccess(response.message())
                        dataPeriodikView.hideLoading()
                        dataPeriodikView.hideProgress()
                    }
                    else
                    {
                        dataPeriodikView.onError(response.message())
                        dataPeriodikView.hideLoading()
                        dataPeriodikView.hideProgress()
                    }
                }

            })
        }
        else
        {
            dataPeriodikView.inputKosong()
            dataPeriodikView.hideLoading()
            dataPeriodikView.hideProgress()
        }
    }
}