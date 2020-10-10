package com.example.pendaftaransekolahmvp.presenter.login

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemLogin

//todo 2 (login) kerangka respon dari login
interface LoginView {
    fun onSuccess(message:String,results: List<ResultsItemLogin?>?);
    fun onError(message:String);
    fun inputKosong();
    fun hideProgress()
    fun hideLoading()
}