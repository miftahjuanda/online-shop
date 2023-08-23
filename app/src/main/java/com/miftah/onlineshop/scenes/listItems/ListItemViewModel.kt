package com.miftah.onlineshop.scenes.listItems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.onlineshop.networking.NetworkManager
import com.miftah.onlineshop.scenes.listItems.Model.ListResponse
import kotlinx.coroutines.launch

class ListItemViewModel: ViewModel() {
    val listItems: MutableLiveData<ListResponse> = MutableLiveData()

    fun getListProduct() {
        viewModelScope.launch {
            listItems.value = NetworkManager.retrofit.getListProduct()
        }
    }

}