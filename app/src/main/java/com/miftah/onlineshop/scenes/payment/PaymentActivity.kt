package com.miftah.onlineshop.scenes.payment

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.ActivityPaymentBinding
import com.miftah.onlineshop.model.ChangeStatusModel
import com.miftah.onlineshop.model.OrderDetails
import com.miftah.onlineshop.model.OrderModel
import com.miftah.onlineshop.model.PaymentModel
import com.miftah.onlineshop.scenes.MainActivity
import com.miftah.onlineshop.scenes.listItems.ListItemViewModel
import com.miftah.onlineshop.utilities.GenerateOrderStatus
import com.miftah.onlineshop.utilities.SharedPreferenceManager
import com.miftah.onlineshop.utilities.convertRupiah
import java.net.URLEncoder


class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private var isView: Boolean = false

    private lateinit var viewModel: PaymentViewModel
    var paymentModel: PaymentModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PaymentViewModel::class.java]
        paymentModel = intent.extras?.getParcelable("paymentModel")
        isView = intent.getBooleanExtra("isViewPayment", isView)

        eventBtn()
        bindData()
        observeValue()
    }

    private fun bindData() {
        Glide.with(this)
            .load("https://umkm2m.com/storage/" + paymentModel?.image)
            .error(R.drawable.empty_image)
            .into(binding.itemImage)

        binding.paymentTitleTv.text = paymentModel?.title
        binding.paymentHargaTv.text = paymentModel?.price?.let { convertRupiah(it) }
        binding.paymentTotalItemsTv.text = "${paymentModel?.item} ${paymentModel?.unit}"

        val totalHarga = (paymentModel?.price ?: 0.0) * (paymentModel?.item?.toInt() ?: 0)
        binding.paymentTotalHargaTv.text = convertRupiah(totalHarga)

        val user = SharedPreferenceManager(this).getUser()
        binding.namaTv.text = user?.nama
        binding.phoneNumberTv.text = user?.phone
        binding.addressTv.text = user?.address
        binding.postalCodeTv.text = "${user?.postalCode}"
        binding.orderStatusLayout.isVisible = isView

        if (isView) {
            binding.codeOrderTv.text = "#"+ paymentModel?.orderCode
            binding.statusOrderPayment.text = paymentModel?.orderStatus?.toInt()
                ?.let { GenerateOrderStatus(it) }

            when (paymentModel?.orderStatus) {
                "1" -> {
                    binding.checkoutBtn.setBackgroundColor(Color.GRAY)
                    binding.checkoutBtn.setTextColor(Color.WHITE)
                    binding.checkoutBtn.text = "Cancel My Order"
                }
                "2","3" -> {
                    binding.checkoutBtn.setBackgroundColor(Color.RED)
                    binding.checkoutBtn.setTextColor(Color.WHITE)
                    binding.checkoutBtn.text = "Complete the order"
                }
                else -> {
                    binding.checkoutBtn.isVisible = false
                }
            }
        } else {

        }
    }

    private fun eventBtn() {
        binding.backIv.setOnClickListener {
            finish()
        }

        binding.checkoutBtn.setOnClickListener {
            if (isView) {
                when (paymentModel?.orderStatus) {
                    "1" -> {
                        val statusModel = ChangeStatusModel(paymentModel?.id ?: 0, 1)
                        viewModel.updateOrder(statusModel)
                    }
                    "2","3" -> {
                        val statusModel = ChangeStatusModel(paymentModel?.id ?: 0, 2)
                        viewModel.updateOrder(statusModel)
                    }
                }
            } else {
                orderProcess()
            }
        }
    }

    private fun observeValue() {
        viewModel.orderResponse.observe(this, Observer {
            if (it.status == 201) {
                openWhatsapp()
            }
        })

        viewModel.resultStatus.observe(this, Observer {
            finish()
        })
    }

    private fun orderProcess() {
        val user = SharedPreferenceManager(this).getUser()
        val orderDetails: ArrayList<OrderDetails> = arrayListOf()

        orderDetails.add(OrderDetails(paymentModel?.id, paymentModel?.item?.toInt()))
        val paymentModel = OrderModel(user?.nama, user?.phone, user?.address, user?.postalCode.toString(), orderDetails)

        viewModel.postOrdering(paymentModel)
    }

    private fun openWhatsapp() {
        val contact = "6281263513463"

        val totalHarga = (paymentModel?.price ?: 0.0) * (paymentModel?.item?.toInt() ?: 0)

        val user = SharedPreferenceManager(this).getUser()
        val headerMsg: String = "Terimakasih Bapak/ibu ${user?.nama}. \nAnda telah berhasil melakukan pemesanan: "
        val bodyMsg: String = "\n\nProduk: ${paymentModel?.title} - ${convertRupiah(paymentModel?.price ?: 0.0)}" +
                "\nJumlah barang: ${paymentModel?.item} ${paymentModel?.unit}" +
                "\nTotal: ${convertRupiah(totalHarga)}"
        val alamat: String = "\nAlamat: ${user?.address} \nKodepos: ${user?.postalCode} \nNomor HP: ${user?.phone}"
        val bottom: String = "\n\nSilahkan melakukan pembayaran sebesar ${convertRupiah(totalHarga)}." +
                "\nKirim bukti transaksi untuk dapat melanjutkan ke proses pengiriman." +
                "\nBank Mandiri- 090xxx - nama" + "\nBank BRI- 010xxx - nama"

        val url = "https://api.whatsapp.com/send?phone=$contact" + "&text=" + URLEncoder.encode(headerMsg+bodyMsg+alamat+bottom,"UTF-8")

        println("url wa: $url")
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

        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
    }
}