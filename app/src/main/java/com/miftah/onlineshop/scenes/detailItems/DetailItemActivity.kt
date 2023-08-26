package com.miftah.onlineshop.scenes.detailItems

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.ActivityDetailItemBinding
import com.miftah.onlineshop.model.PaymentModel
import com.miftah.onlineshop.scenes.editProfile.EditProfileActivity
import com.miftah.onlineshop.scenes.payment.PaymentActivity
import com.miftah.onlineshop.utilities.SharedPreferenceManager
import com.miftah.onlineshop.utilities.convertRupiah
import me.relex.circleindicator.CircleIndicator
import java.text.NumberFormat
import java.util.Locale


class DetailItemActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var indicator: CircleIndicator
    private lateinit var binding: ActivityDetailItemBinding

    private val detailData: DetailItemActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventButton()
        setImagePager()
        bindData()
    }

    private fun bindData() {
        val price: Int = detailData.dataDetail.productPrice.toString().toInt()
        val stock = detailData.dataDetail.productStock
        var totalOrder: Int = 1

        binding.detailTitle.text = detailData.dataDetail.productName
        binding.detailUmkm.text = detailData.dataDetail.umkmName
        binding.detailCategory.text = detailData.dataDetail.productCategory
        binding.detailDescription.text = detailData.dataDetail.productDescription
//        binding.detailUnit.text = " /${detailData.dataDetail.productUnit}"
        binding.detailPrice.text = "${(convertRupiah((price * totalOrder).toDouble()))}"

        binding.plusBtn.setOnClickListener {
            if (totalOrder < stock ) {
                totalOrder += 1
                binding.totalItemsText.text = "$totalOrder"
                binding.detailPrice.text = "${(convertRupiah((price * totalOrder).toDouble()))}"
            }
        }

        binding.minBtn.setOnClickListener {
            if (totalOrder > 1 ) {
                totalOrder -= 1
                binding.totalItemsText.text = "$totalOrder"
                binding.detailPrice.text = "${(convertRupiah((price * totalOrder).toDouble()))}"
            }
        }

    }

    private fun eventButton() {
        binding.backIv.setOnClickListener {
            finish()
        }

        binding.orderNowBtn.setOnClickListener {
            val user = SharedPreferenceManager(this).getUser()

            if (user != null) {
                moveToPayment()
            } else {
                openEditData()
            }

        }
    }

    private fun openEditData() {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }

    private fun moveToPayment() {
        val dataOrder = PaymentModel(detailData.dataDetail.productId,
            detailData.dataDetail.productName,
            detailData.dataDetail.productImages.first().imagePath,
            detailData.dataDetail.productPrice.toDouble(),
            detailData.dataDetail.productUnit,
            binding.totalItemsText.text.toString())

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("paymentModel", dataOrder)
        startActivity(intent)
    }

    private fun setImagePager() {
        viewPager = binding.viewpager //findViewById(R.id.viewpager)
        imageSliderAdapter = ImageSliderAdapter(this, detailData.dataDetail.productImages)
        viewPager.adapter = imageSliderAdapter
        indicator = binding.indicator //findViewById(R.id.indicator)
        indicator.setViewPager(viewPager)
    }
}