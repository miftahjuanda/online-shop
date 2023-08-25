package com.miftah.onlineshop.networking

import com.miftah.onlineshop.model.OrderModel
import com.miftah.onlineshop.model.OrderResponse
import com.miftah.onlineshop.scenes.listItems.Model.ListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UmkmApi {
    @GET("product/product-list")
    suspend fun getListProduct(): ListResponse

    @POST("order/place-order")
    suspend fun postOrdering(@Body data: OrderModel): OrderResponse
}