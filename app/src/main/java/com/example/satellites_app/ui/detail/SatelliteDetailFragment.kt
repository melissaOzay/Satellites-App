package com.example.satellites_app.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.satellites_app.base.BaseFragment
import com.example.satellites_app.databinding.FragmentDetailSatelliteBinding
import com.example.satellites_app.utility.DateFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    override fun onResume() {
        super.onResume()
        arguments?.let {
            val id = SatelliteDetailFragmentArgs.fromBundle(it).id
            viewModel.getSatellites(id)
            viewModel.getSatellitePosition(id.toString())
        }
        observeSatelliteDetail()
        errorMessage()

    }

    override fun initUI() {
        super.initUI()
        observeSatelliteDetail()

    }

    private fun errorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeSatelliteDetail() {
        viewModel.satelliteDetail.observe(viewLifecycleOwner) {
            it?.map {
                arguments?.let { arg ->
                    val name = SatelliteDetailFragmentArgs.fromBundle(arg).name
                    with(binding) {
                        tvName.text = name
                        val date = it.firstFlight
                        val newDate = DateFormat.format(date)
                        tvFirstFlight.text = newDate
                        val heightMass = "${it.height}/${it.mass}"
                        binding.tvHeight.text = heightMass
                        binding.tvCost.text = it.costPerLaunch.toString()
                    }

                }
            }
        }

        viewModel.satellitePosition.observe(viewLifecycleOwner) { satellitePositions ->
            satellitePositions?.forEach { satellitePosition ->
                lifecycleScope.launch {
                    val positionFirstNumber =
                        "(${satellitePosition.positions[0].posX},${satellitePosition.positions[0].posY})"
                    binding.tvPosition.text = positionFirstNumber
                    satellitePosition.positions.forEach { position ->
                        delay(3000)
                        val positionNumber = "(${position.posX},${position.posY})"
                        binding.tvPosition.text = positionNumber

                    }
                }
            }
        }
    }

}