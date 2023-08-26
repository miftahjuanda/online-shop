package com.miftah.onlineshop.scenes.detailItems

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.miftah.onlineshop.R
import com.miftah.onlineshop.scenes.listItems.Model.ProductImage
import java.util.Objects

class ImageSliderAdapter(val context: Context, val imageList: List<ProductImage>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = mLayoutInflater.inflate(R.layout.detail_image_slider, container, false)
        val imageView: ImageView = itemView.findViewById<View>(R.id.images_slider_iv) as ImageView

        Glide.with(context)
            .load("https://umkm2m.com/storage/" + imageList[position].imagePath)
            .error(R.drawable.empty_image)
            .into(imageView)

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as ConstraintLayout)
    }
}