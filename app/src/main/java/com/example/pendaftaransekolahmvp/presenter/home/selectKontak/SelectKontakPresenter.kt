package com.example.pendaftaransekolahmvp.presenter.home.selectKontak

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseKontak
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 4 (selectKontak) construct interaface
class SelectKontakPresenter(var selectKontakView: SelectKontakView) {

    // todo 2 (selectKontak) membuat Respon selectKontak
    fun selectKontak(idUser:String)
    {
        // todo 3 (selectKontak) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectKontak(idUser).enqueue(object : Callback<ResponseKontak>{
            override fun onFailure(call: Call<ResponseKontak>, t: Throwable) {
                selectKontakView.onErrorSelectKontak(t.localizedMessage)
                selectKontakView.hideLoadingSelectKontak()
                selectKontakView.hideProgressSelectKontak()
            }

            override fun onResponse(
                call: Call<ResponseKontak>,
                response: Response<ResponseKontak>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectKontakView.onSuccessSelectKontak(response.message(),data)
                        selectKontakView.hideLoadingSelectKontak()
                        selectKontakView.hideProgressSelectKontak()
                    }
                    else
                    {
                        selectKontakView.onErrorSelectKontak(response.message())
                        selectKontakView.hideLoadingSelectKontak()
                        selectKontakView.hideProgressSelectKontak()
                    }
                }
                else
                {
                    selectKontakView.onErrorSelectKontak(response.message())
                    selectKontakView.hideLoadingSelectKontak()
                    selectKontakView.hideProgressSelectKontak()
                }
            }

        })
    }
}