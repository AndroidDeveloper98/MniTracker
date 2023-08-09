package com.innovation.moneytracker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innovation.moneytracker.databinding.DemoCellBinding
import com.innovation.moneytracker.databinding.DemoInnerCellBinding
import com.innovation.moneytracker.models.SuggestionListState


class DemoInnerListAdapter(
    private val mContext: Context,
    private val list: List<SuggestionListState.Item>
) : RecyclerView.Adapter<DemoInnerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DemoInnerCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            holder.binding.tvName.text = list[position].paidTo
            holder.binding.tvAmount.text = list[position].amount
            holder.binding.tvMessage.text = list[position].message
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: DemoInnerCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}
