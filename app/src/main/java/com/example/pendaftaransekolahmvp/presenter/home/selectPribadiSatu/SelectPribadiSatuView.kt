package com.example.pendaftaransekolahmvp.presenter.home.selectPribadiSatu

import com.example.pendaftaransekolahmvp.model.kotlin.ResultItem

//todo 1 (selectPribadiSatu) kerangka respon
interface SelectPribadiSatuView {
    fun onSuccessSelect(
        message:String,
        results: List<ResultItem?>?
    )
    fun onErrorSelect(message:String)
    fun hideLoadingSelect()
    fun hideProgressSelect()
}