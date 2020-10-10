package com.example.pendaftaransekolahmvp.presenter.home.selectDataPrestasi

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseDataPrestasi
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectDataPrestasi) construct interface
class SelectDataPrestasiPresenter(var selectDataPrestasiView: SelectDataPrestasiView) {

    //todo 2 (selectDataPrestasi) membuat fungsi selectDataPrestasi
    fun selectDataPrestasi(idUser:String)
    {
        //todo 3 (selectDataPrestasi) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectDataPrestasi(idUser).enqueue(object : Callback<ResponseDataPrestasi>{
            override fun onFailure(call: Call<ResponseDataPrestasi>, t: Throwable) {
                selectDataPrestasiView.onErrorSelectDataPrestasi(t.localizedMessage)
                selectDataPrestasiView.hideLoadingSelectDataPrestasi()
                selectDataPrestasiView.hideProgressSelectDataPrestasi()
            }

            override fun onResponse(
                call: Call<ResponseDataPrestasi>,
                response: Response<ResponseDataPrestasi>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectDataPrestasiView.onSuccessSelectDataPrestasi(response.message(),data)
                        selectDataPrestasiView.hideLoadingSelectDataPrestasi()
                        selectDataPrestasiView.hideProgressSelectDataPrestasi()
                    }
                    else
                    {
                        selectDataPrestasiView.onErrorSelectDataPrestasi(response.message())
                        selectDataPrestasiView.hideLoadingSelectDataPrestasi()
                        selectDataPrestasiView.hideProgressSelectDataPrestasi()
                    }
                }
                else
                {
                    selectDataPrestasiView.onErrorSelectDataPrestasi(response.message())
                    selectDataPrestasiView.hideLoadingSelectDataPrestasi()
                    selectDataPrestasiView.hideProgressSelectDataPrestasi()
                }
            }

        })
    }
}