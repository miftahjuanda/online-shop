package com.miftah.onlineshop.scenes.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miftah.onlineshop.R
import com.miftah.onlineshop.model.OrderListModel

class OrderItemAdapter(private val orderList : ArrayList<OrderListModel>, private  val itemClickAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<OrderItemAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderItemAdapter.OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_list_vertical, parent, false)
        return OrderItemAdapter.OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderItemAdapter.OrderViewHolder, position: Int) {
        val currentItem = orderList[position]
        holder.title.text = currentItem.title
        holder.subtitle.text = "${currentItem.item} item • ${currentItem.totalPrice}"
        holder.status.text = currentItem.status

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

    }
    interface ItemAdapterCallback {
        fun onCLick(v: View, data: OrderListModel)
    }

}

//internal fun statusView(status: String): TextView {
//    when(status){
//
//    }
//}