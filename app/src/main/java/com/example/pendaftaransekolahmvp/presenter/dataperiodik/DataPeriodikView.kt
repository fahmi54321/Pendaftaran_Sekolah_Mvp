package com.example.pendaftaransekolahmvp.presenter.dataperiodik

//todo 2 (dataPeriodik) kerangka respon
interface DataPeriodikView {
    fun onSuccess(message:String)
    fun onError(message:String)
    fun hideLoading()
    fun hideProgress()
    fun inputKosong()
}