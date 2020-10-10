package com.example.pendaftaransekolahmvp.presenter.ibukandung

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseIbuKandung
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 5 (ibu kandung) konstruct interface
class IbuKandungPresenter(var ibuKandungView: IbuKandungView) {

    //todo 3 (ibu kandung) membuat fungsi tambahIbuKandung
    fun tambahIbuKandung(idUser:String,namaIbu:String,nikIbu:String,thnIbu:String,pddkIbu:String,pkjIbu:String,pbaIbu:String,bkhIbu:String)
    {
        if (idUser.isNotEmpty() && namaIbu.isNotEmpty() && nikIbu.isNotEmpty() && pddkIbu.isNotEmpty() && pkjIbu.isNotEmpty() && pbaIbu.isNotEmpty() && bkhIbu.isNotEmpty())
        {
            // todo 4 (ibu kandung) konfigurasi retrofit
            ConfigNetwork.getRetrofit().tambahIbuKandung(idUser,namaIbu,nikIbu,thnIbu,pddkIbu,pkjIbu,pbaIbu,bkhIbu).enqueue(object : Callback<ResponseIbuKandung>{
                override fun onFailure(call: Call<ResponseIbuKandung>, t: Throwable) {
                    ibuKandungView.onError(t.localizedMessage)
                    ibuKandungView.hideLoading()
                    ibuKandungView.hideProgress()
                }

                override fun onResponse(
                    call: Call<ResponseIbuKandung>,
                    response: Response<ResponseIbuKandung>
                ) {
                    if (response.isSuccessful)
                    {
                        ibuKandungView.onSuccess(response.message())
                        ibuKandungView.hideLoading()
                        ibuKandungView.hideProgress()
                    }
                    else
                    {
                        ibuKandungView.onError(response.message())
                        ibuKandungView.hideLoading()
                        ibuKandungView.hideProgress()
                    }
                }

            })
        }
        else
        {
            ibuKandungView.inputKosong()
            ibuKandungView.hideLoading()
            ibuKandungView.hideProgress()
        }
    }
}