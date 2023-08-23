package com.miftah.onlineshop.scenes.editProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.ActivityEditProfileBinding
import com.miftah.onlineshop.scenes.payment.PaymentActivity

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)

        eventButton()
    }

    private fun eventButton() {
        binding.backIv.setOnClickListener {
            finish()
        }

        binding.saveProfileBtn.setOnClickListener {
            Toast.makeText(this,"save data", Toast.LENGTH_SHORT).show()
            finish()
//            val intent = Intent(this, PaymentActivity::class.java)
//            startActivity(intent)
        }
    }
}