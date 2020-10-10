package com.example.pendaftaransekolahmvp.presenter.pribadilima

// todo 2 (pribadiLima) kerangka respon
interface PribadiLimaView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
}