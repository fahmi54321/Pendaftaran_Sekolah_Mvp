package com.example.pendaftaransekolahmvp.presenter.login

import android.util.Log
import com.example.pendaftaransekolahmvp.model.kotlin.ResponseUser
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo 5 (login) konstruct interface
class LoginPresenter(var loginView: LoginView) {
    //todo 3 (login) membuat fungsi login
    fun login(emailAddress:String,password:String)
    {
        if (emailAddress.isNotEmpty() && password.isNotEmpty())
        {

            //todo 4 (login) konfigurasi rx
            ConfigNetwork.getRetrofit().login(emailAddress,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response->
                    val data =response.results
                    if (data?.size?:0>0)
                    {
                        loginView.onSuccess(response.message?:"",data)
                        loginView.hideLoading()
                        loginView.hideProgress()
                        Log.d("Respon Berhasil",response.message?:"")
                    }
                    else
                    {
                        loginView.onError(response.message?:"")
                        loginView.hideLoading()
                        loginView.hideProgress()
                        Log.d("Data Tidak Ada",response.message?:"")
                    }
                },{
                    t ->
                    loginView.onError(t.localizedMessage)
                    loginView.hideLoading()
                    loginView.hideProgress()
                    Log.d("Respon Gagal",t.localizedMessage)
                })


//            //todo 4 (login) konfigurasi retrofit
//            ConfigNetwork.getRetrofit().login(emailAddress,password).enqueue(object : Callback<ResponseUser>{
//                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
//                    loginView.onError(t.localizedMessage)
//                    loginView.hideLoading()
//                    loginView.hideProgress()
//                    Log.d("Respon Gagal",t.localizedMessage)
//                }
//
//                override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
//
//                    val data =response.body()?.results
//                    if (data?.size?:0>0)
//                    {
//                        loginView.onSuccess(response.message(),data)
//                        loginView.hideLoading()
//                        loginView.hideProgress()
//                        Log.d("Respon Berhasil",response.message())
//                    }
//                    else
//                    {
//                        loginView.onError(response.message())
//                        loginView.hideLoading()
//                        loginView.hideProgress()
//                        Log.d("Data Tidak Ada",response.message())
//                    }
//                }
//
//            })
        }
        else
        {
            loginView.inputKosong()
            loginView.hideLoading()
            loginView.hideProgress()
        }
    }
}