package com.example.satellites_app.ui.satellitedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.satellites_app.base.BaseFragment
import com.example.satellites_app.databinding.FragmentDetailSatelliteBinding
import com.example.satellites_app.utility.DateFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatelliteDetailFragment : BaseFragment<FragmentDetailSatelliteBinding, SatelliteDetailVM>() {

    override val viewModel: SatelliteDetailVM by activityViewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentDetailSatelliteBinding {
        return FragmentDetailSatelliteBinding.inflate(layoutInflater)
    }

    private val args: SatelliteDetailFragmentArgs by navArgs()

    private val name by lazy {
        args.name
    }

    override fun initUI() {
        super.initUI()
        val id = args.id
        viewModel.getSatelliteDetail(id)
        viewModel.getSatellitePositions(id.toString())
        observeSatelliteDetail()
        observeErrorMessage()
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeSatelliteDetail() {
        viewModel.satelliteDetail.observe(viewLifecycleOwner) {
            val name = name
            with(binding) {
                tvName.text = name
                val date = it.firstFlight
                val newDateFormat = DateFormat.format(date)
                val heightMass = "${it.height}/${it.mass}"
                tvFirstFlight.text = newDateFormat
                tvHeight.text = heightMass
                tvCost.text = it.costPerLaunch.toString()
            }

        }

        viewModel.satellitePosition.observe(viewLifecycleOwner) {
            val positionNumber = "(${it.posX},${it.posY})"
            binding.tvPosition.text = positionNumber
        }
    }

}