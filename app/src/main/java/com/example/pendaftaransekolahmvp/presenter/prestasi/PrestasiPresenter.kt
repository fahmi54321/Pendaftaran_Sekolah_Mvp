package com.example.pendaftaransekolahmvp.presenter.prestasi

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseDataPrestasi
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (tambahPrestasi) construct interface
class PrestasiPresenter(var prestasiView: PrestasiView) {

    //todo 3 (tambahPrestasi) membuat fungsi tambahPrestasi
    fun tambahPrestasi(id:String,jenisPrestasi:String,tingkat:String,namaPrestasiSatu:String,tahunSatu:String,penyelenggaraSatu:String,namaPrestasiDua:String,tahunDua:String,penyelenggaraDua:String,namaPrestasiTiga:String,tahunTiga:String,penyelenggaraTiga:String)
    {
        if (id.isNotEmpty())
        {
            //todo 4 (tambahPrestasi) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahDataPrestasi(id,jenisPrestasi,tingkat,namaPrestasiSatu,tahunSatu,penyelenggaraSatu,namaPrestasiDua,tahunDua,penyelenggaraDua,namaPrestasiTiga,tahunTiga,penyelenggaraTiga).enqueue(object : Callback<ResponseDataPrestasi>{
                override fun onFailure(call: Call<ResponseDataPrestasi>, t: Throwable) {
                    prestasiView.onError(t.localizedMessage)
                    prestasiView.hideLoading()
                    prestasiView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponseDataPrestasi>,
                    response: Response<ResponseDataPrestasi>
                ) {
                    if (response.isSuccessful)
                    {
                        prestasiView.onSuccess(response.message())
                        prestasiView.hideProgress()
                        prestasiView.hideLoading()
                    }
                    else
                    {
                        prestasiView.onError(response.message())
                        prestasiView.hideProgress()
                        prestasiView.hideLoading()
                    }
                }

            })
        }
        else
        {
            prestasiView.inputKosong()
            prestasiView.hideProgress()
            prestasiView.hideLoading()
        }
    }
}