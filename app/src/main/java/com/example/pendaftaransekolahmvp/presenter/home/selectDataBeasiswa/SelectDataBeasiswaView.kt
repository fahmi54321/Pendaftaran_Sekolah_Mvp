package com.example.pendaftaransekolahmvp.presenter.home.selectDataBeasiswa

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemDataBeasiswa

//todo 1 (selectDataBeasiswa) kerangka respon
interface SelectDataBeasiswaView {
    fun onSuccessSelectDataBeasiswa(
        message:String,
        results: List<ResultsItemDataBeasiswa?>?
    )
    fun onErrorSelectDataBeasiswa(message:String)
    fun hideLoadingSelectDataBeasiswa()
    fun hideProgressSelectDataBeasiswa()
}