package com.miftah.onlineshop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentUser(
    val nama: String = "",
    val phone: String = "",
    val address: String = "",
    val postalCode: Int = 0
): Parcelable