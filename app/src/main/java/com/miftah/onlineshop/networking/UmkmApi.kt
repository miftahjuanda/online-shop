package com.miftah.onlineshop.networking

import com.miftah.onlineshop.scenes.listItems.Model.ListResponse
import retrofit2.http.GET

interface UmkmApi {
    @GET("product/product-list")
    suspend fun getListProduct(): ListResponse

}