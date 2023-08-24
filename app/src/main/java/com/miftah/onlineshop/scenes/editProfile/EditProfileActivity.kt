package com.miftah.onlineshop.scenes.editProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.ActivityEditProfileBinding
import com.miftah.onlineshop.model.CurrentUser
import com.miftah.onlineshop.scenes.payment.PaymentActivity
import com.miftah.onlineshop.utilities.SharedPreferenceManager

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)
        bindData()
        eventButton()
    }

    private fun bindData() {
        val user = SharedPreferenceManager(this).getUser()

        binding.editName.setText(user?.nama)
        binding.editPhone.setText(user?.phone)
        binding.editAddress.setText(user?.address)
        binding.editPostalCode.setText(user?.postalCode.toString())
    }
    private fun eventButton() {
        binding.backIv.setOnClickListener {
            finish()
        }

        binding.saveProfileBtn.setOnClickListener {
            val name = binding.editName.text
            val phone = binding.editPhone.text
            val address = binding.editAddress.text
            val postal = binding.editPostalCode.text

            if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty() && !postal.isEmpty()) {
                val user = CurrentUser(name.toString(), phone.toString(), address.toString(), postal.toString().toInt())
                SharedPreferenceManager(this).saveUSer(user)
                finish()
            } else {
                Toast.makeText(this,"Lengkapi field yang masih kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}