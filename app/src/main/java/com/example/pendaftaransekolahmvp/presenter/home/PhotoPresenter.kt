package com.example.pendaftaransekolahmvp.presenter.home

import com.example.pendaftaransekolahmvp.model.kotlin.ResponsePhotoSekolah
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo 4 (ambil Photo Sekolah RecyclerView) konstruct interface
class PhotoPresenter(var photoView: PhotoView) {

    // todo 2 (ambil Photo Sekolah RecyclerView) membuat fungsi getPhoto
    fun getPhoto()
    {
        // todo 3 (ambil Photo Sekolah RecyclerView) konfigurasi retrofit
        ConfigNetwork.getRetrofit().selectPhotoSekolah().enqueue(object : Callback<ResponsePhotoSekolah>{
            override fun onFailure(call: Call<ResponsePhotoSekolah>, t: Throwable) {
                photoView.onErrorPhoto(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponsePhotoSekolah>,
                response: Response<ResponsePhotoSekolah>
            ) {
                val data =response.body()?.results
                if (response.isSuccessful)
                {
                    if (data?.size?:0>0)
                    {
                        photoView.onSuccessPhoto(response.message(),data)
                    }
                    else
                    {
                        photoView.onErrorPhoto(response.message())
                    }
                }
            }

        })
    }
}