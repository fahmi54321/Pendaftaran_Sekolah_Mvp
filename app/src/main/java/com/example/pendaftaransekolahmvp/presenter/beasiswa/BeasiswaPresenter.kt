package com.example.pendaftaransekolahmvp.presenter.beasiswa

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseDataBeasiswa
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (tambahBeasiswa) construct interface
class BeasiswaPresenter(var beasiswaView: BeasiswaView) {

    //todo 3 (tambahBeasiswa) membuat fungsi tambahBeasiswa
    fun tambahBeasiswa(idUser:String,jenisBeasiswa1:String,keterangan1:String,tahunMulai1:String,tahunSelesai1:String,jenisBeasiswa2:String,keterangan2:String,tahunMulai2:String,tahunSelesai2:String,jenisBeasiswa3:String,keterangan3:String,tahunMulai3:String,tahunSelesai3:String)
    {
        if (idUser.isNotEmpty())
        {
            //todo 4 (tambahBeasiswa) konfigurai retrofit
            ConfigNetwork.getRetrofit().tambahDataBeasiswa(idUser,jenisBeasiswa1,keterangan1,tahunMulai1,tahunSelesai1,jenisBeasiswa2,keterangan2,tahunMulai2,tahunSelesai2,jenisBeasiswa3,keterangan3,tahunMulai3,tahunSelesai3).enqueue(object : Callback<ResponseDataBeasiswa>{
                override fun onFailure(call: Call<ResponseDataBeasiswa>, t: Throwable) {
                    beasiswaView.onError(t.localizedMessage)
                    beasiswaView.hideLoading()
                    beasiswaView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponseDataBeasiswa>,
                    response: Response<ResponseDataBeasiswa>
                ) {
                    if (response.isSuccessful)
                    {
                        beasiswaView.onSuccess(response.message())
                        beasiswaView.hideLoading()
                        beasiswaView.hideProgress()
                    }
                    else
                    {
                        beasiswaView.onError(response.message())
                        beasiswaView.hideLoading()
                        beasiswaView.hideProgress()
                    }
                }

            })
        }
        else
        {
            beasiswaView.inputKosong()
            beasiswaView.hideLoading()
            beasiswaView.hideProgress()
        }
    }
}