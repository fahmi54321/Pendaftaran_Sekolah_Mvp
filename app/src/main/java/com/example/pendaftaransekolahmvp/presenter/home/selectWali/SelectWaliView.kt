package com.example.pendaftaransekolahmvp.presenter.home.selectWali

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemWali

//todo 1 (selectWali) kerangka respon
interface SelectWaliView {
    fun onSuccessSelectWali(
        message:String,
        results: List<ResultsItemWali?>?
    )
    fun onErrorSelectWali(message:String)
    fun hideLoadingSelectWali()
    fun hideProgressSelectWali()
}