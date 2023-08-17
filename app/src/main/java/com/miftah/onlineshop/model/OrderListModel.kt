package com.miftah.onlineshop.model

class OrderListModel (title: String, totalPrice: String, status: String, item: Int) {
    var title: String = ""
    var totalPrice: String = ""
    var status: String = ""
    var item: Int = 1

    init {
        this.title = title
        this.totalPrice = totalPrice
        this.status = status
        this.item = item
    }
}