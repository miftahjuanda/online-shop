package com.miftah.onlineshop.scenes.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.FragmentProfileBinding
import com.miftah.onlineshop.scenes.editProfile.EditProfileActivity
import com.miftah.onlineshop.scenes.payment.PaymentActivity
import com.miftah.onlineshop.utilities.SharedPreferenceManager

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        bindData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventButton()
    }

    private fun bindData() {
        val user = SharedPreferenceManager(requireContext()).getUser()
        binding.profileNama.text = user?.nama
        binding.profilePhone.text = user?.phone
    }

    private fun eventButton() {
        binding.editProfileLayout.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

}