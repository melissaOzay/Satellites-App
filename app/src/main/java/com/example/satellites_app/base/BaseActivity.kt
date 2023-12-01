package com.example.satellites_app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.satellites_app.ui.progressbar.LoadingDialog

abstract class BaseActivity<VM : BaseViewModel, B : ViewBinding> : AppCompatActivity() {
    abstract val viewModel: VM
    private var loadingDialog: LoadingDialog? = null
    private var _binding: B? = null
    private val binding get() = _binding
    open fun initialize() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLoadingDialog()
        _binding = getViewBinding()
        setContentView(binding?.root)
        initialize()
    }

    private fun observeLoadingDialog() {
        viewModel.loadingState.observe(this) {
            if (it.equals(true)) {
                if (loadingDialog == null) {
                    loadingDialog = LoadingDialog(this)
                }
                loadingDialog?.showLoading()
            } else {
                loadingDialog?.hideLoading()
            }
        }
    }

    abstract fun getViewBinding(): B
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}