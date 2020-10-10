package com.example.pendaftaransekolahmvp.presenter.pribadidua

import android.util.Log
import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiDua
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 5 (pribadiDua) konstruct interface
class PribadiDuaPresenter(var pribadiDuaView: PribadiDuaView) {

    // todo 3 (pribadiDua) membuat fungsi tambahPribadiDua
    fun tambahPribadiDua(idUser:String,noAkte:String,agama:String,kwn:String,berkebutuhanKhusus:String,alamat:String,rt:String,rw:String)
    {
        if (noAkte.isNotEmpty() && agama.isNotEmpty() && kwn.isNotEmpty() && berkebutuhanKhusus.isNotEmpty() && alamat.isNotEmpty() && rt.isNotEmpty() && rw.isNotEmpty())
        {
            // todo 4 (pribadiDua) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahPribadiDua(idUser,noAkte,agama,kwn,berkebutuhanKhusus,alamat,rt,rw).enqueue(object : Callback<ResponsePribadiDua>{
                override fun onFailure(call: Call<ResponsePribadiDua>, t: Throwable) {
                    pribadiDuaView.onError(t.localizedMessage)
                    pribadiDuaView.hideLoading()
                    pribadiDuaView.hideProgress()
                    Log.d("Response Gagal total",t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponsePribadiDua>,
                    response: Response<ResponsePribadiDua>
                ) {
                    if (response.isSuccessful)
                    {
                        pribadiDuaView.onSuccess(response.message())
                        pribadiDuaView.hideLoading()
                        pribadiDuaView.hideProgress()
                        Log.d("Response Berhasil",response.message())
                    }
                    else
                    {
                        pribadiDuaView.onError(response.message())
                        pribadiDuaView.hideLoading()
                        pribadiDuaView.hideProgress()
                        Log.d("Response Gagal",response.message())
                    }
                }

            })
        }
        else
        {
            pribadiDuaView.inputKosong()
            pribadiDuaView.hideLoading()
            pribadiDuaView.hideProgress()
        }
    }
}