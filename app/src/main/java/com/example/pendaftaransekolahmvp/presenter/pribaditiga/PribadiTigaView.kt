package com.example.pendaftaransekolahmvp.presenter.pribaditiga

//todo 2 (pribadiTiga) kerangka respon
interface PribadiTigaView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}