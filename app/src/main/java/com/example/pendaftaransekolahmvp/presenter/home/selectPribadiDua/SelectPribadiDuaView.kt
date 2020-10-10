package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiDua

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemPribadiDua

//todo 1 (selectPribadiDua) kerangka respon
interface SelectPribadiDuaView {
    fun onSuccessSelectPribadiDua(
        message:String,
        results: List<ResultsItemPribadiDua?>?
    )
    fun onErrorSelectPribadiDua(message:String)
    fun hideLoadingSelectPribadiDua()
    fun hideProgressSelectPribadiDua()
}