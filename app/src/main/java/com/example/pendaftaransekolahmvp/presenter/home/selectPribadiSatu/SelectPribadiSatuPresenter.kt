package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiSatu

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiSatuKotlin
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectPribadiSatu) construct interface
class SelectPribadiSatuPresenter(var selectPribadiSatuView: SelectPribadiSatuView) {

    //todo 2 (selectPribadiSatu) membuat fungsi selectPribadiSatu
    fun selectPribadiSatu(idUser:String)
    {
        //todo 3 (selectPribadiSatu) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectPribadiSatu(idUser).enqueue(object : Callback<ResponsePribadiSatuKotlin>{
            override fun onFailure(call: Call<ResponsePribadiSatuKotlin>, t: Throwable) {
                selectPribadiSatuView.onErrorSelect(t.localizedMessage)
                selectPribadiSatuView.hideLoadingSelect()
                selectPribadiSatuView.hideProgressSelect()
            }

            override fun onResponse(
                call: Call<ResponsePribadiSatuKotlin>,
                response: Response<ResponsePribadiSatuKotlin>
            ) {
                if (response.isSuccessful)
                {
                    val data =response.body()?.result
                    if (data?.size?:0>0)
                    {
                        selectPribadiSatuView.onSuccessSelect(response.message(),data)
                        selectPribadiSatuView.hideLoadingSelect()
                        selectPribadiSatuView.hideProgressSelect()
                    }
                    else
                    {
                        selectPribadiSatuView.onErrorSelect(response.message())
                        selectPribadiSatuView.hideLoadingSelect()
                        selectPribadiSatuView.hideProgressSelect()
                    }
                }
                else
                {
                    selectPribadiSatuView.onErrorSelect(response.message())
                    selectPribadiSatuView.hideLoadingSelect()
                    selectPribadiSatuView.hideProgressSelect()
                }
            }

        })
    }
}