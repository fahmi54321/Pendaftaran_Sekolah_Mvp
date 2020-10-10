package com.example.pendaftaransekolahmvp.presenter.home.selectDataPeriodik

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemDataPeriodik

//todo 1 (selectDataPeriodik) kerangka respon
interface SelectDataPeriodikView {
    fun onSuccessSelectDataPeriodik(
        message:String,
        results: List<ResultsItemDataPeriodik?>?
    )
    fun onErrorSelectDataPeriodik(message:String)
    fun hideLoadingSelectDataPeriodik()
    fun hideProgressSelectDataPeriodik()
}