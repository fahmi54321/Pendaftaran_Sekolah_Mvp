package com.example.pendaftaransekolahmvp.presenter.home.selectAyahKandung

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseAyahKandung
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 4 (selectAyahKandung) construct interface
class SelectAyahKandungPresenter(var selectAyahKandungView: SelectAyahKandungView) {

    // todo 2 (selectAyahKandung) membuat fungsi selectAyahKandung
    fun selectAyahKandung(idUser:String)
    {
        // todo 3 (selectAyahKandung) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectAyahKandung(idUser).enqueue(object : Callback<ResponseAyahKandung>{
            override fun onFailure(call: Call<ResponseAyahKandung>, t: Throwable) {
                selectAyahKandungView.onErrorSelectAyahKandung(t.localizedMessage)
                selectAyahKandungView.hideLoadingSelectAyahKandung()
                selectAyahKandungView.hideProgressSelectAyahKandung()
            }

            override fun onResponse(
                call: Call<ResponseAyahKandung>,
                response: Response<ResponseAyahKandung>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectAyahKandungView.onSuccessSelectAyahKandung(response.message(),data)
                        selectAyahKandungView.hideLoadingSelectAyahKandung()
                        selectAyahKandungView.hideProgressSelectAyahKandung()
                    }
                    else
                    {
                        selectAyahKandungView.onErrorSelectAyahKandung(response.message())
                        selectAyahKandungView.hideLoadingSelectAyahKandung()
                        selectAyahKandungView.hideProgressSelectAyahKandung()
                    }
                }
                else
                {
                    selectAyahKandungView.onErrorSelectAyahKandung(response.message())
                    selectAyahKandungView.hideLoadingSelectAyahKandung()
                    selectAyahKandungView.hideProgressSelectAyahKandung()
                }
            }

        })
    }
}