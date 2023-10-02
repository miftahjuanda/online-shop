package com.miftah.onlineshop.scenes.listItems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.onlineshop.R
import com.miftah.onlineshop.scenes.listItems.Model.DatumProduct
import com.miftah.onlineshop.utilities.convertRupiah

class ListItemAdapter(private val homeList: ArrayList<DatumProduct>?, private val itemClickAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<ListItemAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_vertical, parent, false)
        return  HomeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return homeList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        if (homeList != null) {
            val currentItem = homeList[position]
            holder.titleHome.text = currentItem?.productName ?: ""

            val price = convertRupiah((currentItem?.productPrice ?: "").toString().toDouble())
            holder.subTitleHome.text = "$price • ${currentItem.productUnit}"

            Glide.with(holder.itemView.context)
                .load("https://umkm2m.com/storage/" + currentItem.productImages.first().imagePath)
                .error(R.drawable.empty_image)
                .into(holder.imageList)

            holder.itemView.setOnClickListener {
                itemClickAdapterCallback.onCLick(holder.itemView, homeList[position])
            }
        }
    }

    class HomeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleHome: TextView = itemView.findViewById(R.id.title_list_tv)
        val subTitleHome: TextView = itemView.findViewById(R.id.subtitle_list_tv)
        val imageList: ImageView = itemView.findViewById(R.id.home_list_img)
    }

    interface ItemAdapterCallback {
        fun onCLick(v: View, data: DatumProduct)
    }
}