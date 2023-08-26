package com.miftah.onlineshop.utilities


fun GenerateOrderStatus(code: Int): String {
    when (code) {
        1 -> { return "Menunggu pembayaran" }
        2 -> { return "Proses pengemasan" }
        3 -> { return "Dalam pengiriman" }
        4 -> { return "Pesanan selesai" }
        5 -> { return "Pesanan dibatalkan" }
        else -> return "-"
    }
}