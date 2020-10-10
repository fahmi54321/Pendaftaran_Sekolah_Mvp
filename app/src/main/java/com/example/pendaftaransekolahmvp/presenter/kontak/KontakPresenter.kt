package com.example.pendaftaransekolahmvp.presenter.kontak

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseKontak
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 5 (tambahKontak) contruct interface
class KontakPresenter(var kontakView: KontakView) {

    // todo 3 (tambahKontak) membuat fungsi tambahKontak
    fun tambahKontak(idUser:String,noTelpRumah:String,noHp:String,emailAddress:String)
    {
        if (noTelpRumah.isNotEmpty() && noHp.isNotEmpty() && emailAddress.isNotEmpty())
        {
            // todo 4 (tambahKontak) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahKontak(idUser,noTelpRumah,noHp,emailAddress).enqueue(object : Callback<ResponseKontak>{
                override fun onFailure(call: Call<ResponseKontak>, t: Throwable) {
                    kontakView.onError(t.localizedMessage)
                    kontakView.hideLoading()
                    kontakView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponseKontak>,
                    response: Response<ResponseKontak>
                ) {
                    if (response.isSuccessful)
                    {
                        kontakView.onSuccess(response.message())
                        kontakView.hideLoading()
                        kontakView.hideProgress()
                    }
                    else
                    {
                        kontakView.onError(response.message())
                        kontakView.hideLoading()
                        kontakView.hideProgress()
                    }
                }

            })
        }
        else
        {
            kontakView.inputKosong()
            kontakView.hideLoading()
            kontakView.hideProgress()
        }
    }
}