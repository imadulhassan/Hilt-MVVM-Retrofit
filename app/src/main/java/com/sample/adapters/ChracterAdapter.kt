package com.sample.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewbinding.ViewBinding
import com.sample.databinding.RvItemBinding
import com.sample.extn.extractName
import com.sample.models.RelatedTopic


class ChracterAdapter : Adapter<ChracterAdapter.ItemHolder>() {
    private var itemList = mutableListOf<RelatedTopic>()
    private var filteredItemList = mutableListOf<RelatedTopic>()
    private var adapterItemClick: AdapterItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView =
            RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(filteredItemList[position])
        holder.itemView.setOnClickListener {
            holder.itemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return filteredItemList.size
    }

    fun updateList(items: ArrayList<RelatedTopic>) {
        itemList.addAll(items)
        filterList("")
    }

    fun setCallback(callback: AdapterItemClick) {
        adapterItemClick = callback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(query: String) {
        filteredItemList.clear()
        filteredItemList.addAll(itemList.filter {
            it.text.contains(query, ignoreCase = true) || it.result.contains(
                query,
                ignoreCase = true
            )
        })
        notifyDataSetChanged()
    }

    inner class ItemHolder(mbinding: ViewBinding) : RecyclerView.ViewHolder(mbinding.root) {
        private var binding: RvItemBinding = mbinding as RvItemBinding
        fun bindData(itemdata: RelatedTopic) {
            binding.idTVCourse.text = extractName(itemdata.text)
        }

        fun itemClick(position: Int) {
            adapterItemClick?.onItemClick(position)
        }

    }
}
