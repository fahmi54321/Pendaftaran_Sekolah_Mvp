package com.example.pendaftaransekolahmvp.presenter.prestasi

//todo 2 (tambahPrestasi) kerangka respon
interface PrestasiView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}