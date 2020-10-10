package com.example.pendaftaransekolahmvp.presenter.home.selectDataPeriodik

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseDataPeriodik
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 4 (selectDataPeriodik) construct interface
class SelectDataPeriodikPresenter(var selectDataPeriodikView: SelectDataPeriodikView) {

    //todo 2 (selectDataPeriodik) membuat fungsi selectDataPeriodik
    fun selectDataPeriodik(idUser:String)
    {
        //todo 3 (selectDataPeriodik) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectDataPeriodik(idUser).enqueue(object : Callback<ResponseDataPeriodik>{
            override fun onFailure(call: Call<ResponseDataPeriodik>, t: Throwable) {
                selectDataPeriodikView.onErrorSelectDataPeriodik(t.localizedMessage)
                selectDataPeriodikView.hideLoadingSelectDataPeriodik()
                selectDataPeriodikView.hideProgressSelectDataPeriodik()
            }

            override fun onResponse(
                call: Call<ResponseDataPeriodik>,
                response: Response<ResponseDataPeriodik>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        selectDataPeriodikView.onSuccessSelectDataPeriodik(response.message(),data)
                        selectDataPeriodikView.hideLoadingSelectDataPeriodik()
                        selectDataPeriodikView.hideProgressSelectDataPeriodik()
                    }
                    else
                    {
                        selectDataPeriodikView.onErrorSelectDataPeriodik(response.message())
                        selectDataPeriodikView.hideLoadingSelectDataPeriodik()
                        selectDataPeriodikView.hideProgressSelectDataPeriodik()
                    }
                }
                else
                {
                    selectDataPeriodikView.onErrorSelectDataPeriodik(response.message())
                    selectDataPeriodikView.hideLoadingSelectDataPeriodik()
                    selectDataPeriodikView.hideProgressSelectDataPeriodik()
                }
            }

        })
    }
}