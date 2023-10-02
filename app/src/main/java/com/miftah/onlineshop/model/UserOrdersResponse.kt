package com.miftah.onlineshop.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class UserOrdersResponse (
    val status: String,
    val message: String,
    val data: List<DatumOrders>
)

data class DatumOrders (
    @SerializedName("order_id")
    val orderID: Int,
    @SerializedName("order_code")
    val orderCode: String,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("item_amount")
    val itemAmount: Int,
    @SerializedName("price_amount")
    val priceAmount: Int,
    @SerializedName("base_price")
    val basePrice: Int,
    @SerializedName("order_status")
    val orderStatus: String,
    @SerializedName("product_unit")
    val productUnit: String,
    @SerializedName("product_name")
    val productName: String
)