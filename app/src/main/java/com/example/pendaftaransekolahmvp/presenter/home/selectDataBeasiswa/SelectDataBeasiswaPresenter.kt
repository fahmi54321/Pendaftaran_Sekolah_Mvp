package com.example.pendaftaransekolahmvp.presenter.home.selectDataBeasiswa

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseDataBeasiswa
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//todo 4 (selectDataBeasiswa) construct interface
class SelectDataBeasiswaPresenter(var selectDataBeasiswaView: SelectDataBeasiswaView) {

    //todo 2 (selectDataBeasiswa) membuat fungsi selectDataBeasiswa
    fun selectDataBeasiswa(idUser:String)
    {
        //todo 3 (selectDataBeasiswa) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectDataBeasiswa(idUser).enqueue(object : Callback<ResponseDataBeasiswa>{
            override fun onFailure(call: Call<ResponseDataBeasiswa>, t: Throwable) {
                selectDataBeasiswaView.onErrorSelectDataBeasiswa(t.localizedMessage)
                selectDataBeasiswaView.hideLoadingSelectDataBeasiswa()
                selectDataBeasiswaView.hideProgressSelectDataBeasiswa()
            }

            override fun onResponse(
                call: Call<ResponseDataBeasiswa>,
                response: Response<ResponseDataBeasiswa>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectDataBeasiswaView.onSuccessSelectDataBeasiswa(response.message(),data)
                        selectDataBeasiswaView.hideLoadingSelectDataBeasiswa()
                        selectDataBeasiswaView.hideProgressSelectDataBeasiswa()
                    }
                    else
                    {
                        selectDataBeasiswaView.onErrorSelectDataBeasiswa(response.message())
                        selectDataBeasiswaView.hideLoadingSelectDataBeasiswa()
                        selectDataBeasiswaView.hideProgressSelectDataBeasiswa()
                    }
                }
                else
                {
                    selectDataBeasiswaView.onErrorSelectDataBeasiswa(response.message())
                    selectDataBeasiswaView.hideLoadingSelectDataBeasiswa()
                    selectDataBeasiswaView.hideProgressSelectDataBeasiswa()
                }
            }

        })
    }
}