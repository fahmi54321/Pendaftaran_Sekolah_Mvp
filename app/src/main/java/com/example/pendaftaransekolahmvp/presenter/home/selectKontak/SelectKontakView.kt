package com.example.pendaftaransekolahmvp.presenter.home.selectKontak

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemKontak

// todo 1 (selectKontak) kerangka respon
interface SelectKontakView {
    fun onSuccessSelectKontak(
        message:String,
        results: List<ResultsItemKontak?>?
    )
    fun onErrorSelectKontak(message:String)
    fun hideLoadingSelectKontak()
    fun hideProgressSelectKontak()
}