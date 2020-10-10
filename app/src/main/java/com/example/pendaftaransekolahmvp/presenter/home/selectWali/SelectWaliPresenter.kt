package com.example.pendaftaransekolahmvp.presenter.home.selectWali

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseWali
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectWali) construct interface
class SelectWaliPresenter(var selectWaliView: SelectWaliView) {

    //todo 2 (selectWali) membuat fungsi selectWali
    fun selectWali(idUser:String)
    {
        //todo 3 (selectWali) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectWali(idUser).enqueue(object : Callback<ResponseWali>{
            override fun onFailure(call: Call<ResponseWali>, t: Throwable) {
                selectWaliView.onErrorSelectWali(t.localizedMessage)
                selectWaliView.hideLoadingSelectWali()
                selectWaliView.hideProgressSelectWali()
            }

            override fun onResponse(call: Call<ResponseWali>, response: Response<ResponseWali>) {
                if (response.isSuccessful)
                {
                    var data=response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectWaliView.onSuccessSelectWali(response.message(),data)
                        selectWaliView.hideLoadingSelectWali()
                        selectWaliView.hideProgressSelectWali()
                    }
                    else
                    {
                        selectWaliView.onErrorSelectWali(response.message())
                        selectWaliView.hideLoadingSelectWali()
                        selectWaliView.hideProgressSelectWali()
                    }
                }
                else
                {
                    selectWaliView.onErrorSelectWali(response.message())
                    selectWaliView.hideLoadingSelectWali()
                    selectWaliView.hideProgressSelectWali()
                }
            }

        })
    }
}