package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiTiga

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemPribadiTiga

//todo 1 (selectPribadiTiga) kerangka respon
interface selectPribadiTigaView {
    fun onSuccessSelectPribadiTiga(
        message:String,
        results: List<ResultsItemPribadiTiga?>?
    )
    fun onErrorSelectPribadiTiga(message:String)
    fun hideLoadingSelectPribadiTiga()
    fun hideProgressSelectPribadiTiga()
}