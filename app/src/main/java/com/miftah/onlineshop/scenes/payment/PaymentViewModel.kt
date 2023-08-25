package com.miftah.onlineshop.scenes.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.onlineshop.model.OrderModel
import com.miftah.onlineshop.model.OrderResponse
import com.miftah.onlineshop.networking.NetworkManager
import com.miftah.onlineshop.scenes.listItems.Model.ListResponse
import kotlinx.coroutines.launch
import retrofit2.Callback

class PaymentViewModel: ViewModel() {
    val orderResponse: MutableLiveData<OrderResponse> = MutableLiveData()

    fun postOrdering(order: OrderModel) {
        viewModelScope.launch {
            orderResponse.value = NetworkManager.retrofit.postOrdering(order)
        }
    }
}
