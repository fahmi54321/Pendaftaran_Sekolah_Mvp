package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiEmpat

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemPribadiEmpat

//todo 1 (selectPribadiEmpat) kerangka respon
interface SelectPribadiEmpatView {
    fun onSuccessSelectPribadiEmpat(
        message:String,
        results: List<ResultsItemPribadiEmpat?>?
    )
    fun onErrorSelectPribadiEmpat(message:String)
    fun hideLoadingSelectPribadiEmpat()
    fun hideProgressSelectPribadiEmpat()
}