package com.example.pendaftaransekolahmvp.presenter.home.selectAyahKandung

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemAyahKandung

// todo 1 (selectAyahKandung) kerangka respon
interface SelectAyahKandungView {
    fun onSuccessSelectAyahKandung(
        message:String,
        results: List<ResultsItemAyahKandung?>?
    )
    fun onErrorSelectAyahKandung(message:String)
    fun hideLoadingSelectAyahKandung()
    fun hideProgressSelectAyahKandung()
}