package com.example.pendaftaransekolahmvp.presenter.wali

//todo 2 (wali) kerangka respon
interface WaliView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}