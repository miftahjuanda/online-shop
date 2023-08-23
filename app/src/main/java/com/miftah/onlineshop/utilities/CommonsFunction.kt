package com.miftah.onlineshop.utilities

import java.text.NumberFormat
import java.util.Locale

internal fun convertRupiah(number: Double): String? {
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(number)
}