package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiTiga

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiTiga
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectPribadiTiga) construct interface
class selectPribadiTigaPresenter(var selectPribadiTigaView: selectPribadiTigaView) {

    //todo 2 (selectPribadiTiga) membuat fungsi selectPribadiTiga
    fun selectPribadiTiga(idUser:String)
    {
        //todo 3 (selectPribadiTiga) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectPribadiTiga(idUser).enqueue(object : Callback<ResponsePribadiTiga>{
            override fun onFailure(call: Call<ResponsePribadiTiga>, t: Throwable) {
                selectPribadiTigaView.onErrorSelectPribadiTiga(t.localizedMessage)
                selectPribadiTigaView.hideLoadingSelectPribadiTiga()
                selectPribadiTigaView.hideProgressSelectPribadiTiga()
            }

            override fun onResponse(
                call: Call<ResponsePribadiTiga>,
                response: Response<ResponsePribadiTiga>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectPribadiTigaView.onSuccessSelectPribadiTiga(response.message(),data)
                        selectPribadiTigaView.hideLoadingSelectPribadiTiga()
                        selectPribadiTigaView.hideProgressSelectPribadiTiga()
                    }
                    else
                    {
                        selectPribadiTigaView.onErrorSelectPribadiTiga(response.message())
                        selectPribadiTigaView.hideLoadingSelectPribadiTiga()
                        selectPribadiTigaView.hideProgressSelectPribadiTiga()
                    }
                }
                else
                {
                    selectPribadiTigaView.onErrorSelectPribadiTiga(response.message())
                    selectPribadiTigaView.hideLoadingSelectPribadiTiga()
                    selectPribadiTigaView.hideProgressSelectPribadiTiga()
                }
            }

        })
    }
}