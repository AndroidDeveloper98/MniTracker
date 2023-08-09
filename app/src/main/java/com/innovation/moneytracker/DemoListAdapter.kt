package com.innovation.moneytracker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innovation.moneytracker.databinding.DemoCellBinding
import com.innovation.moneytracker.models.SuggestionListState


class DemoListAdapter(
    private val mContext: Context,
    private val list: Map<String, List<SuggestionListState.Item>>
) : RecyclerView.Adapter<DemoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DemoCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val key = list.keys.toList()[position]
            val itemList = list[key]
            holder.binding.tvName.text = key
            itemList?.let {
                initializeRecyclerView(holder.binding.rvNotesList, itemList)
            }
        }
    }

    private fun initializeRecyclerView(rvList: RecyclerView, folderList: List<SuggestionListState.Item>) {
        val notesListAdapter = DemoInnerListAdapter(mContext, folderList)
        rvList.apply {
            layoutManager = LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = notesListAdapter
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: DemoCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}
