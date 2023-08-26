package com.miftah.onlineshop.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentModel(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",
    var price: Double = 0.0,
    var unit: String = "",
    var item: String = "",
    var orderCode: String = "",
    var orderStatus: String = ""
): Parcelable

data class OrderModel (
    @SerializedName("customer_name"    ) var customerName    : String?                 = null,
    @SerializedName("customer_phone"   ) var customerPhone   : String?                 = null,
    @SerializedName("shipping_address" ) var shippingAddress : String?                 = null,
    @SerializedName("postal_code"      ) var postalCode      : String?                 = null,
    @SerializedName("order_details"    ) var orderDetails    : ArrayList<OrderDetails> = arrayListOf()

)
data class OrderDetails (
    @SerializedName("product_id" ) var productId : Int? = null,
    @SerializedName("amount"     ) var amount    : Int? = null
)

data class OrderResponse (
    @SerializedName("status" ) var status : Int?    = null,
    @SerializedName("error"  ) var error  : String? = null
)

data class UserOrderModel (
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("customer_phone")
    val customerPhone: String
)

data class ChangeStatusModel(
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("order_status")
    val orderStatus: Int
)
