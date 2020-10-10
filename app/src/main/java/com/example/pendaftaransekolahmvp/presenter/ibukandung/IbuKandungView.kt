package com.example.pendaftaransekolahmvp.presenter.ibukandung

//todo 2 (ibu kandung) kerangka respon
interface IbuKandungView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}