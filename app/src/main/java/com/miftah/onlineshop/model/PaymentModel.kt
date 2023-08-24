package com.miftah.onlineshop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentModel(
    val title: String = "",
    val image: String = "",
    var price: Double = 0.0,
    var unit: String = "",
    var item: String = ""
): Parcelable