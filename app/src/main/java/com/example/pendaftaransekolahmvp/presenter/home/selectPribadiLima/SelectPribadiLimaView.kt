package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiLima

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemPribadiLima

//todo 1 (selectPribadiLima) kerangka respon
interface SelectPribadiLimaView {
    fun onSuccessSelectPribadiLima(
        message:String,
        results: List<ResultsItemPribadiLima?>?
    )
    fun onErrorSelectPribadiLima(message:String)
    fun hideLoadingSelectPribadiLima()
    fun hideProgressSelectPribadiLima()
}