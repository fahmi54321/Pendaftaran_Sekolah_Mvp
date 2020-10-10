package com.example.pendaftaransekolahmvp.presenter.home

import com.example.pendaftaransekolahmvp.model.kotlin.ResponseUser
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (getFotoProfile) konstruct interface
class HomePresenter(var homeView: HomeView) {

    //todo 3 (getFotoProfile) membuat fungsi
    fun getProfile(idUser:String)
    {
        //todo 4 (getFotoProfile) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectUser(idUser).enqueue(object : Callback<ResponseUser>{
            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                homeView.onError(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                val data =response.body()?.results
                if (response.isSuccessful)
                {
                    homeView.onSuccess(response.message(),data)
                }
                else
                {
                    homeView.onError(response.message())
                }
            }

        })
    }
}