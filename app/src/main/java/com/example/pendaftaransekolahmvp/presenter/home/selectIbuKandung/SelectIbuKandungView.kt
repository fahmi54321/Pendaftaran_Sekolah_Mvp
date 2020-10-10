package com.example.pendaftaransekolahmvp.presenter.home.selectIbuKandung

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemIbuKandung

// todo 1 (selectIbuKandung) kerangka respon
interface SelectIbuKandungView {
    fun onSuccessSelectIbuKandung(
        message:String,
        results: List<ResultsItemIbuKandung?>?
    )
    fun onErrorSelectIbuKandung(message:String)
    fun hideLoadingSelectIbuKandung()
    fun hideProgressSelectIbuKandung()
}