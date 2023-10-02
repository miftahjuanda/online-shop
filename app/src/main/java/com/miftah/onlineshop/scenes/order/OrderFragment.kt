package com.miftah.onlineshop.scenes.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.FragmentOrderBinding
import com.miftah.onlineshop.model.DatumOrders
import com.miftah.onlineshop.model.OrderListModel
import com.miftah.onlineshop.model.PaymentModel
import com.miftah.onlineshop.model.UserOrderModel
import com.miftah.onlineshop.scenes.editProfile.EditProfileActivity
import com.miftah.onlineshop.scenes.payment.PaymentActivity
import com.miftah.onlineshop.utilities.SharedPreferenceManager

class OrderFragment : Fragment(), OrderItemAdapter.ItemAdapterCallback {

    private var listOrder: ArrayList<DatumOrders> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderItemAdapter
    private lateinit var viewModel: OrderViewModel
    private lateinit var binding: FragmentOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModelProvider(requireActivity())[OrderViewModel::class.java]
        listOrder = arrayListOf()

        val getUser = SharedPreferenceManager(requireContext()).getUser()
        getUser?.let { user ->
            viewModel.getListOrder(UserOrderModel(user.nama, user.phone))
            observeData()
        } ?: kotlin.run {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[OrderViewModel::class.java]
        listOrder = arrayListOf()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.orderListRv
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = OrderItemAdapter(listOrder, this)
        recyclerView.adapter = adapter


        val getUser = SharedPreferenceManager(requireContext()).getUser()
        getUser?.let { user ->
            viewModel.getListOrder(UserOrderModel(user.nama, user.phone))
            observeData()
        } ?: kotlin.run {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeData() {
        viewModel.orderList.observe(requireActivity(), Observer {

            if (it.data != null) {
                listOrder.clear()
                it.data.forEach { it1 ->
                    listOrder.add(it1)
                }

                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCLick(v: View, data: DatumOrders) {
        val dataOrder = PaymentModel(
            data.orderID,
            data.productName,
            data.productImage,
            data.basePrice.toDouble(),
            data.productUnit,
            data.itemAmount.toString(),
            0,
            "-",
            "-",
            "-",
            "-",
            data.orderCode,
            data.orderStatus
        )

        val intent = Intent(requireContext(), PaymentActivity::class.java)
        intent.putExtra("paymentModel", dataOrder)
        intent.putExtra("isViewPayment", true)
        startActivity(intent)
    }

}