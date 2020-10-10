package com.example.pendaftaransekolahmvp.presenter.home.selectIbuKandung

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseIbuKandung
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 4 (selectIbuKandung) construct interface
class SelectIbuKandungPresenter(var selectIbuKandungView: SelectIbuKandungView) {

    // todo 2 (selectIbuKandung) membuat fungsi selectIbuKandung
    fun selectIbuKandung(idUser:String)
    {
        // todo 3 (selectIbuKandung) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectIbuKandung(idUser).enqueue(object : Callback<ResponseIbuKandung>{
            override fun onFailure(call: Call<ResponseIbuKandung>, t: Throwable) {
                selectIbuKandungView.onErrorSelectIbuKandung(t.localizedMessage)
                selectIbuKandungView.hideLoadingSelectIbuKandung()
                selectIbuKandungView.hideProgressSelectIbuKandung()
            }

            override fun onResponse(
                call: Call<ResponseIbuKandung>,
                response: Response<ResponseIbuKandung>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectIbuKandungView.onSuccessSelectIbuKandung(response.message(),data)
                        selectIbuKandungView.hideLoadingSelectIbuKandung()
                        selectIbuKandungView.hideProgressSelectIbuKandung()
                    }
                    else
                    {
                        selectIbuKandungView.onErrorSelectIbuKandung(response.message())
                        selectIbuKandungView.hideLoadingSelectIbuKandung()
                        selectIbuKandungView.hideProgressSelectIbuKandung()
                    }
                }
                else
                {
                    selectIbuKandungView.onErrorSelectIbuKandung(response.message())
                    selectIbuKandungView.hideLoadingSelectIbuKandung()
                    selectIbuKandungView.hideProgressSelectIbuKandung()
                }
            }

        })
    }
}