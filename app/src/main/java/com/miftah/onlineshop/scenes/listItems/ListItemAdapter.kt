package com.miftah.onlineshop.scenes.listItems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miftah.onlineshop.R
import com.miftah.onlineshop.model.HomeListModel

class ListItemAdapter(private val homeList : ArrayList<HomeListModel>, private  val itemClickAdapterCallback: ItemAdapterCallback,) : RecyclerView.Adapter<ListItemAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_vertical, parent, false)
        return  HomeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  homeList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = homeList[position]
        holder.titleHome.text = currentItem.title
        holder.subTitleHome.text = currentItem.price
    }

    class HomeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleHome: TextView = itemView.findViewById(R.id.title_list_tv)
        val subTitleHome: TextView = itemView.findViewById(R.id.subtitle_list_tv)
    }

    interface ItemAdapterCallback {
        fun onCLick(v: View, data: HomeListModel)
    }
}