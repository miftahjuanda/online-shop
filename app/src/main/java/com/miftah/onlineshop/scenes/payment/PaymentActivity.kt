package com.miftah.onlineshop.scenes.payment

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.ActivityPaymentBinding
import java.net.URLEncoder


class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private var isView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isView = intent.getBooleanExtra("isViewPayment", isView)
        eventBtn()

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
                openWhatsapp()
            }
        }
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