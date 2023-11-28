package com.example.satellites_app

import androidx.activity.viewModels
import com.example.satellites_app.base.BaseActivity
import com.example.satellites_app.databinding.ActivityMainBinding
import com.example.satellites_app.ui.splash.SplashVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<SplashVM, ActivityMainBinding>() {
    override val viewModel: SplashVM by viewModels()

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}