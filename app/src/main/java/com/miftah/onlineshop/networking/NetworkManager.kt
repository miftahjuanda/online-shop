package com.miftah.onlineshop.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://umkm2m.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UmkmApi::class.java)
    }

    val postRetrofit = Retrofit.Builder()
    .baseUrl("https://umkm2m.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}