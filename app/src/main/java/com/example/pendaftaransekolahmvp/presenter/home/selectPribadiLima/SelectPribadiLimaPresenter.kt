package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiLima

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiLima
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectPribadiLima) construct interface
class SelectPribadiLimaPresenter(var selectPribadiLimaView: SelectPribadiLimaView) {

    //todo 2 (selectPribadiLima) membuat fungsi selectPribadiLima
    fun selectPribadiLima(idUser:String)
    {
        //todo 3 (selectPribadiLima) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectPribadiLima(idUser).enqueue(object : Callback<ResponsePribadiLima>{
            override fun onFailure(call: Call<ResponsePribadiLima>, t: Throwable) {
                selectPribadiLimaView.onErrorSelectPribadiLima(t.localizedMessage)
                selectPribadiLimaView.hideLoadingSelectPribadiLima()
                selectPribadiLimaView.hideProgressSelectPribadiLima()
            }

            override fun onResponse(
                call: Call<ResponsePribadiLima>,
                response: Response<ResponsePribadiLima>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectPribadiLimaView.onSuccessSelectPribadiLima(response.message(),data)
                        selectPribadiLimaView.hideLoadingSelectPribadiLima()
                        selectPribadiLimaView.hideProgressSelectPribadiLima()
                    }
                    else
                    {
                        selectPribadiLimaView.onErrorSelectPribadiLima(response.message())
                        selectPribadiLimaView.hideLoadingSelectPribadiLima()
                        selectPribadiLimaView.hideProgressSelectPribadiLima()
                    }
                }
                else
                {
                    selectPribadiLimaView.onErrorSelectPribadiLima(response.message())
                    selectPribadiLimaView.hideLoadingSelectPribadiLima()
                    selectPribadiLimaView.hideProgressSelectPribadiLima()
                }
            }

        })
    }
}