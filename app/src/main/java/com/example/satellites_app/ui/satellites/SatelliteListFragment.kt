package com.example.satellites_app.ui.satellites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.satellites_app.base.BaseFragment
import com.example.satellites_app.databinding.FragmentListSatelliteBinding
import com.example.satellites_app.ui.satellites.adapter.SatelliteListAdapter
import com.example.satellites_app.utility.SatelliteListAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatelliteListFragment : BaseFragment<FragmentListSatelliteBinding, SatelliteListVM>() {

    override val viewModel: SatelliteListVM by activityViewModels()

    private val adapter by lazy {
        SatelliteListAdapter(object : SatelliteListAdapterListener {
            override fun clickDetail(id: Int, name: String) {
                navigateToDetailFragment(id, name)
            }
        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentListSatelliteBinding {
        return FragmentListSatelliteBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        super.initUI()
        initAdapter()
        observeErrorMessage()
        observeSatelliteList()
        performSearch()

    }

    override fun onDestroyView() {
        binding.rv.adapter=null
        super.onDestroyView()
    }

    private fun navigateToDetailFragment(id: Int, name: String) {
        val action =
            SatelliteListFragmentDirections.actionListScreenFragmentToDetailFragment(id, name)
        findNavController().navigate(action)
    }

    private fun initAdapter() {
        with(binding) {
            rv.adapter = adapter
            rv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeSatelliteList() {
        viewModel.satellites.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            if (list.isEmpty()) {
                binding.message.visibility = View.VISIBLE
            } else {
                binding.message.visibility = View.GONE
            }

        }
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = false

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
                return false
            }
        })

    }

}