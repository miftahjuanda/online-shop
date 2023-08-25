package com.miftah.onlineshop.scenes.payment

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.ActivityPaymentBinding
import com.miftah.onlineshop.model.OrderDetails
import com.miftah.onlineshop.model.OrderModel
import com.miftah.onlineshop.model.PaymentModel
import com.miftah.onlineshop.scenes.listItems.ListItemViewModel
import com.miftah.onlineshop.utilities.SharedPreferenceManager
import com.miftah.onlineshop.utilities.convertRupiah
import java.net.URLEncoder


class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private var isView: Boolean = false

    private lateinit var viewModel: PaymentViewModel
    lateinit var paymentModel: PaymentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PaymentViewModel::class.java]
        paymentModel = intent.extras?.getParcelable("paymentModel")!!
        isView = intent.getBooleanExtra("isViewPayment", isView)

        eventBtn()
        bindData()
        observeValue()
    }

    private fun bindData() {
        Glide.with(this)
            .load("https://umkm2m.com/storage/" + paymentModel.image)
            .error(R.drawable.empty_image)
            .into(binding.itemImage)

        binding.paymentTitleTv.text = paymentModel.title
        binding.paymentHargaTv.text = convertRupiah(paymentModel.price)
        binding.paymentTotalItemsTv.text = "${paymentModel.item} ${paymentModel.unit}"

        val totalHarga = paymentModel.price * paymentModel.item.toInt()
        binding.paymentTotalHargaTv.text = convertRupiah(totalHarga)

        val user = SharedPreferenceManager(this).getUser()
        binding.namaTv.text = user?.nama
        binding.phoneNumberTv.text = user?.phone
        binding.addressTv.text = user?.address
        binding.postalCodeTv.text = "${user?.postalCode}"
    }

    private fun eventBtn() {
        binding.backIv.setOnClickListener {
            finish()
        }

        if (isView) {
            binding.checkoutBtn.setBackgroundColor(Color.RED)
            binding.checkoutBtn.setTextColor(Color.WHITE)
            binding.checkoutBtn.text = "Cancel My Order"
        } else {

        }

        binding.checkoutBtn.setOnClickListener {
            if (isView) {
                finish()
            } else {
                orderProcess()
//                openWhatsapp()
            }
        }
    }

    private fun observeValue() {
        viewModel.orderResponse.observe(this, Observer {
            print("result order: $it")
            finish()
        })
    }

    private fun orderProcess() {
        val user = SharedPreferenceManager(this).getUser()
        val orderDetails: ArrayList<OrderDetails> = arrayListOf()

        orderDetails.add(OrderDetails(paymentModel.id, paymentModel.item.toInt()))
        val paymentModel = OrderModel(user?.nama, user?.phone, user?.address, user?.postalCode.toString(), orderDetails)

        viewModel.postOrdering(paymentModel)
    }

    private fun openWhatsapp() {
        val contact = "628116978000"
        val url = "https://api.whatsapp.com/send?phone=$contact" + "&text=" + URLEncoder.encode("isi pesannya","UTF-8")

        try {
            val pm: PackageManager = this.packageManager
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(
                this,
                "Whatsapp app not installed in your phone",
                Toast.LENGTH_SHORT
            ).show()
            e.printStackTrace()
        }
    }
}