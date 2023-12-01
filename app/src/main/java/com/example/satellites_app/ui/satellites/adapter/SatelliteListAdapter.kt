package com.example.satellites_app.ui.satellites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.satellites_app.R
import com.example.satellites_app.databinding.ItemSatelliteListBinding
import com.example.satellites_app.features.satallite.domain.model.SatellitesModel
import com.example.satellites_app.utility.SatelliteListAdapterListener
import com.example.satellites_app.utility.changeAlpha

class SatelliteListAdapter(private val listener: SatelliteListAdapterListener) :
    ListAdapter<SatellitesModel, SatelliteListAdapter.SatelliteViewHolder>(SatelliteDiffUtil()) {
    companion object {
        private const val ACTIVE_ALPHA = 200
        private const val PASSIVE_ALPHA = 50
    }

    class SatelliteViewHolder(binding: ItemSatelliteListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvName = binding.tvName
        private val tvActive = binding.tvActive
        private val ivActive = binding.ivActive
        private val context: Context = itemView.context
        fun bindItems(listener: SatelliteListAdapterListener, item: SatellitesModel) {
            tvName.text = item.name
            if (item.active) {
                tvActive.setText(R.string.active)
                ivActive.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.green
                    )
                )
                tvActive.changeAlpha(ACTIVE_ALPHA)
                tvName.changeAlpha(ACTIVE_ALPHA)

            } else {
                tvActive.setText(R.string.passive)
                ivActive.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                tvActive.changeAlpha(PASSIVE_ALPHA)
                tvName.changeAlpha(PASSIVE_ALPHA)

            }
            itemView.setOnClickListener {
                listener.clickDetail(item.id, item.name)
            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SatelliteViewHolder {
        val itemBinding =
            ItemSatelliteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatelliteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        holder.bindItems(listener, getItem(position))

    }

    private class SatelliteDiffUtil : DiffUtil.ItemCallback<SatellitesModel>() {

        override fun areContentsTheSame(
            oldItem: SatellitesModel,
            newItem: SatellitesModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: SatellitesModel,
            newItem: SatellitesModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}