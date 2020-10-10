package com.example.pendaftaransekolahmvp.presenter.home.selectDataPrestasi

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemDataPrestasi

//todo 1 (selectDataPrestasi) kerangka respon
interface SelectDataPrestasiView {
    fun onSuccessSelectDataPrestasi(
        message:String,
        results: List<ResultsItemDataPrestasi?>?
    )
    fun onErrorSelectDataPrestasi(message:String)
    fun hideLoadingSelectDataPrestasi()
    fun hideProgressSelectDataPrestasi()
}