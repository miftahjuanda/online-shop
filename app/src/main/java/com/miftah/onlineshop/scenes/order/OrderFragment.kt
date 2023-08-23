package com.miftah.onlineshop.scenes.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.FragmentOrderBinding
import com.miftah.onlineshop.model.OrderListModel
import com.miftah.onlineshop.scenes.payment.PaymentActivity

class OrderFragment : Fragment(), OrderItemAdapter.ItemAdapterCallback {

    private var listItem: ArrayList<OrderListModel> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderItemAdapter

    private lateinit var binding: FragmentOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataDummy()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.orderListRv
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = OrderItemAdapter(listItem, this)
        recyclerView.adapter = adapter
    }

    private fun initDataDummy() {
        listItem = ArrayList()
        listItem.add(OrderListModel("Order 1", "IDR 2.000.000", "In Packaging", 14))
        listItem.add(OrderListModel("Order 2", "IDR 250.000", "In delivery", 2))
        listItem.add(OrderListModel("Order 3", "IDR 400.000", "Cancelled", 4))
        listItem.add(OrderListModel("Order 4", "IDR 1.000.000", "Done", 10))
    }

    override fun onCLick(v: View, data: OrderListModel) {
        Toast.makeText(context, "Order: "+ data.title, Toast.LENGTH_SHORT).show()
        val intent = Intent (context, PaymentActivity::class.java)
        intent.putExtra("isViewPayment", true)
        startActivity(intent)
    }

}