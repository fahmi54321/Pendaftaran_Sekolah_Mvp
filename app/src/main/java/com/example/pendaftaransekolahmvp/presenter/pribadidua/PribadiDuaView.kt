package com.example.pendaftaransekolahmvp.presenter.pribadidua

// todo 2 (pribadiDua) kerangka respon
interface PribadiDuaView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}