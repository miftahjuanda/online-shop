package com.miftah.onlineshop.scenes.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.miftah.onlineshop.R
import com.miftah.onlineshop.scenes.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash_screen)

        Handler().postDelayed( {
            startActivity(
                Intent(this,
                MainActivity::class.java)
            )
        }, 3000)
    }
}