package com.app.dleelk4apps.crazynumbers.ui.start

import android.os.Bundle
import android.app.Activity
import com.app.dleelk4apps.crazynumbers.R
import android.content.Intent

import android.os.Handler






class SplashActivity : Activity() {

    // Splash screen timer
    private val SPLASH_TIME_OUT = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        openStartActivity()
    }

    private fun openStartActivity() {

        Handler().postDelayed({

            val i = Intent(this@SplashActivity, StartActivity::class.java)
            startActivity(i)

            finish()

        }, SPLASH_TIME_OUT.toLong())
    }

}
