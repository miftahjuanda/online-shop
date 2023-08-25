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
    var item: String = ""
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