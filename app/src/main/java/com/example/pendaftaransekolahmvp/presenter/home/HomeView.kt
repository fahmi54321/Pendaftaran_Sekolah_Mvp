package com.example.pendaftaransekolahmvp.presenter.home

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemLogin

//todo 2 (getFotoProfile) kerangka respon dari get Profile
interface HomeView {
    fun onSuccess(message:String,results: List<ResultsItemLogin?>?)
    fun onError(message:String)
}