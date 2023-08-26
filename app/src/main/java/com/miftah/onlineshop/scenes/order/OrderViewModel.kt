package com.miftah.onlineshop.scenes.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.onlineshop.model.UserOrderModel
import com.miftah.onlineshop.model.UserOrdersResponse
import com.miftah.onlineshop.networking.NetworkManager
import kotlinx.coroutines.launch

class OrderViewModel: ViewModel() {
    val orderList: MutableLiveData<UserOrdersResponse> = MutableLiveData()

    fun getListOrder(user: UserOrderModel) {
        viewModelScope.launch {
            orderList.value = NetworkManager.retrofit.getListOrdering(user)
        }
    }
}

class SelfCleaningLiveData<T> : MutableLiveData<T>(){
    override fun onInactive() {
        super.onInactive()
        value = null
    }
}