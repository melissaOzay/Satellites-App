package com.example.satellites_app.ui.listScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.satellites_app.R
import com.example.satellites_app.databinding.ItemSatelliteListBinding
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.utility.ChangeTextColor
import com.example.satellites_app.utility.SatelliteListAdapterListener

class SatelliteListAdapter(private val listener: SatelliteListAdapterListener) :
    ListAdapter<SatelliteListModel, SatelliteListAdapter.SatelliteViewHolder>(SatelliteDiffUtil()) {

    class SatelliteViewHolder(binding: ItemSatelliteListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvName = binding.tvName
        private val tvActive = binding.tvActive
        private val ivActive = binding.ivActive
        private val context: Context = itemView.context
        fun bindItems(listener: SatelliteListAdapterListener, item: SatelliteListModel) {
            tvName.text = item.name
            if (item.active) {
                tvActive.setText(R.string.active)
                ivActive.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.green
                    )
                )
                ChangeTextColor.changeAlpha(200, tvName, tvActive)

            } else {
                tvActive.setText(R.string.passive)
                ivActive.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                ChangeTextColor.changeAlpha(50, tvName, tvActive)
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

    private class SatelliteDiffUtil : DiffUtil.ItemCallback<SatelliteListModel>() {

        override fun areContentsTheSame(
            oldItem: SatelliteListModel,
            newItem: SatelliteListModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: SatelliteListModel,
            newItem: SatelliteListModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}