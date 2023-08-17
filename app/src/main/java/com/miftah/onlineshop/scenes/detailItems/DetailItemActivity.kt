package com.miftah.onlineshop.scenes.detailItems

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.ActivityDetailItemBinding
import com.miftah.onlineshop.scenes.listItems.ListItemAdapter
import com.miftah.onlineshop.scenes.payment.PaymentActivity
import me.relex.circleindicator.CircleIndicator

class DetailItemActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var imageList: List<Int>
    private lateinit var indicator: CircleIndicator

    private lateinit var binding: ActivityDetailItemBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventButton()
        setImagePager()

    }

    private fun eventButton() {
        binding.backIv.setOnClickListener {
            finish()
        }

        binding.orderNowBtn.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setImagePager() {
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.iv_sample_product
        imageList = imageList + R.drawable.ic_sample_product2
        imageList = imageList + R.drawable.iv_sample_product
        imageList = imageList + R.drawable.iv_sample_product

        viewPager = binding.viewpager //findViewById(R.id.viewpager)
        imageSliderAdapter = ImageSliderAdapter(this, imageList)
        viewPager.adapter = imageSliderAdapter
        indicator = binding.indicator //findViewById(R.id.indicator)
        indicator.setViewPager(viewPager)
    }
}