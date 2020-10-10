package com.example.pendaftaransekolahmvp.presenter.home

import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemPhotoSekolah

// todo 1 (ambil Photo Sekolah RecyclerView) kerangka respon
interface PhotoView {
    fun onSuccessPhoto(message:String,results: List<ResultsItemPhotoSekolah?>?)
    fun onErrorPhoto(message:String)
}