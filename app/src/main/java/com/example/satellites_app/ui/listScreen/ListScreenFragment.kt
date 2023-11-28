package com.example.satellites_app.ui.listScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.satellites_app.base.BaseFragment
import com.example.satellites_app.databinding.FragmentListScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListScreenFragment : BaseFragment<FragmentListScreenBinding, ListScreenVM>(){

    override val viewModel: ListScreenVM by activityViewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentListScreenBinding {
       return FragmentListScreenBinding.inflate(layoutInflater)
    }
}