package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiEmpat

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePribadiEmpat
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectPribadiEmpat) construct interface
class SelectPribadiEmpatPresenter(var selectPribadiEmpatView: SelectPribadiEmpatView) {

    //todo 2 (selectPribadiEmpat) membuat fungsi selectPribadiEmpat
    fun selectPribadiEmpat(idUser:String)
    {
        //todo 3 (selectPribadiEmpat) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectPribadiEmpat(idUser).enqueue(object : Callback<ResponsePribadiEmpat>{
            override fun onFailure(call: Call<ResponsePribadiEmpat>, t: Throwable) {
                selectPribadiEmpatView.onErrorSelectPribadiEmpat(t.localizedMessage)
                selectPribadiEmpatView.hideLoadingSelectPribadiEmpat()
                selectPribadiEmpatView.hideProgressSelectPribadiEmpat()
            }

            override fun onResponse(
                call: Call<ResponsePribadiEmpat>,
                response: Response<ResponsePribadiEmpat>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectPribadiEmpatView.onSuccessSelectPribadiEmpat(response.message(),data)
                        selectPribadiEmpatView.hideLoadingSelectPribadiEmpat()
                        selectPribadiEmpatView.hideProgressSelectPribadiEmpat()
                    }
                    else
                    {
                        selectPribadiEmpatView.onErrorSelectPribadiEmpat(response.message())
                        selectPribadiEmpatView.hideLoadingSelectPribadiEmpat()
                        selectPribadiEmpatView.hideProgressSelectPribadiEmpat()
                    }
                }
                else
                {
                    selectPribadiEmpatView.onErrorSelectPribadiEmpat(response.message())
                    selectPribadiEmpatView.hideLoadingSelectPribadiEmpat()
                    selectPribadiEmpatView.hideProgressSelectPribadiEmpat()
                }
            }

        })
    }
}