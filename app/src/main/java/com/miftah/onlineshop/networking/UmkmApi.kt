package com.miftah.onlineshop.networking

import com.miftah.onlineshop.model.ChangeStatusModel
import com.miftah.onlineshop.model.OrderModel
import com.miftah.onlineshop.model.OrderResponse
import com.miftah.onlineshop.model.UserOrderModel
import com.miftah.onlineshop.model.UserOrdersResponse
import com.miftah.onlineshop.scenes.listItems.Model.ListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UmkmApi {
    @GET("product/product-list")
    suspend fun getListProduct(): ListResponse
    @POST("order/place-order")
    suspend fun postOrdering(@Body data: OrderModel): OrderResponse
    @POST("order/user-order")
    suspend fun getListOrdering(@Body data: UserOrderModel): UserOrdersResponse
    @POST("order/change-status")
    suspend fun changeStatus(@Body data: ChangeStatusModel): OrderResponse
}