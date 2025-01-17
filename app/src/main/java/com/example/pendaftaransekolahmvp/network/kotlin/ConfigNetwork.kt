package com.example.pendaftaransekolahmvp.network.kotlin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//todo 1.1 Config network
class ConfigNetwork {
    companion object
    {
        fun getRetrofit() : ConfigApi
        {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(20, TimeUnit.SECONDS)
            builder.connectTimeout(20, TimeUnit.SECONDS)
            builder.writeTimeout(20, TimeUnit.SECONDS)

            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.43.19/PendaftaranSekolah/API/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            val service = retrofit.create(ConfigApi::class.java)

            return service
        }
    }
}