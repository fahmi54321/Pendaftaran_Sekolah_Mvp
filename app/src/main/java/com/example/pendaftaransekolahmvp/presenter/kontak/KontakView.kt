package com.example.pendaftaransekolahmvp.presenter.kontak

// todo 2 (tambahKontak) kerangka respon
interface KontakView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}