package com.miftah.onlineshop.scenes.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.onlineshop.R
import com.miftah.onlineshop.model.DatumOrders
import com.miftah.onlineshop.model.OrderListModel
import com.miftah.onlineshop.model.UserOrdersResponse
import com.miftah.onlineshop.utilities.GenerateOrderStatus

class OrderItemAdapter(private val orderList : List<DatumOrders>, private  val itemClickAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<OrderItemAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderItemAdapter.OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_list_vertical, parent, false)
        return OrderItemAdapter.OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderItemAdapter.OrderViewHolder, position: Int) {
        val currentItem = orderList[position]
        holder.title.text = currentItem.productName
        holder.subtitle.text = "${currentItem.itemAmount} item • ${currentItem.productUnit}"
        holder.status.text = GenerateOrderStatus(currentItem.orderStatus.toInt())

        Glide.with(holder.itemView.context)
            .load("https://umkm2m.com/storage/" + currentItem.productImage)
            .error(R.drawable.empty_image)
            .into(holder.img)

        holder.itemView.setOnClickListener {
            itemClickAdapterCallback.onCLick(holder.itemView, orderList[position])
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    class OrderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.order_title_tv)
        var subtitle: TextView = itemView.findViewById(R.id.order_subtitle_tv)
        var status: TextView = itemView.findViewById(R.id.order_status_tv)
        var img: ImageView = itemView.findViewById(R.id.order_list_img)
    }
    interface ItemAdapterCallback {
        fun onCLick(v: View, data: DatumOrders)
    }

}