package com.example.pendaftaransekolahmvp.presenter.beasiswa

//todo 2 (tambahBeasiswa) kerangka respon
interface BeasiswaView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}