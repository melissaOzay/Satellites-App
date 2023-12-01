package com.example.satellites_app.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import com.example.satellites_app.MainActivity
import com.example.satellites_app.base.BaseActivity
import com.example.satellites_app.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashVM, ActivitySplashBinding>() {
    override val viewModel: SplashVM by viewModels()

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        observeOpenMainView()
    }

    private fun observeOpenMainView() {
        viewModel.canOpenMainScreen.observe(this) {
            if (it) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }
    }
}