package com.example.machinetesttask.task2.adapter

import MatchItemModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetesttask.R
import com.example.machinetesttask.databinding.ItemMatchLayoutBinding

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchItemViewHolder>() {
    inner class MatchItemViewHolder(val binding: ItemMatchLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<MatchItemModel>() {
        override fun areItemsTheSame(oldItem: MatchItemModel, newItem: MatchItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MatchItemModel, newItem: MatchItemModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchItemViewHolder {
        val binding: ItemMatchLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_match_layout, parent, false
        )
        return MatchItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MatchItemViewHolder, position: Int) {

    }
}