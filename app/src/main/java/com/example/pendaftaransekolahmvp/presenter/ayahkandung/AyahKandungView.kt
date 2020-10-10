package com.example.pendaftaransekolahmvp.presenter.ayahkandung

//todo 2 (ayah kandung) kerangka respon
interface AyahKandungView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}