package com.example.pendaftaransekolahmvp.presenter.pribadiempat

//todo 2 (pribadiEmpat) kerangka respon
interface PribadiEmpatView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}