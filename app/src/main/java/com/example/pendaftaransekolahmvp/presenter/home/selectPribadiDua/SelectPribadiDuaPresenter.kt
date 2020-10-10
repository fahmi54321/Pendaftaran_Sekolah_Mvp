package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiDua

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiDua
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectPribadiDua) construct interface
class SelectPribadiDuaPresenter(var selectPribadiDuaView: SelectPribadiDuaView) {

    //todo 2 (selectPribadiDua) membuat fungsi selectPribadiDua
    fun selectPribadiDua(iduser:String)
    {
        //todo 3 (selectPribadiDua) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectPribadiDua(iduser).enqueue(object : Callback<ResponsePribadiDua>{
            override fun onFailure(call: Call<ResponsePribadiDua>, t: Throwable) {
                selectPribadiDuaView.onErrorSelectPribadiDua(t.localizedMessage)
                selectPribadiDuaView.hideLoadingSelectPribadiDua()
                selectPribadiDuaView.hideProgressSelectPribadiDua()
            }

            override fun onResponse(
                call: Call<ResponsePribadiDua>,
                response: Response<ResponsePribadiDua>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectPribadiDuaView.onSuccessSelectPribadiDua(response.message(),data)
                        selectPribadiDuaView.hideLoadingSelectPribadiDua()
                        selectPribadiDuaView.hideProgressSelectPribadiDua()
                    }
                    else
                    {
                        selectPribadiDuaView.onErrorSelectPribadiDua(response.message())
                        selectPribadiDuaView.hideLoadingSelectPribadiDua()
                        selectPribadiDuaView.hideProgressSelectPribadiDua()
                    }
                }
                else
                {
                    selectPribadiDuaView.onErrorSelectPribadiDua(response.message())
                    selectPribadiDuaView.hideLoadingSelectPribadiDua()
                    selectPribadiDuaView.hideProgressSelectPribadiDua()
                }
            }

        })
    }
}