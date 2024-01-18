package com.example.machinetesttask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetesttask.databinding.ItemSongLayoutBinding
import com.example.machinetesttask.model.Item

class TrackItemAdapter : RecyclerView.Adapter<TrackItemAdapter.SongViewHolder>() {

    inner class SongViewHolder(val binding: ItemSongLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.localId == newItem.localId
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding =
            ItemSongLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val holderBinding = holder.binding
        val item = differ.currentList[position]
        val artistAdapter = ArtistAdapter()
        holderBinding.apply {
            tvName.text = item.name
            tvPreviewUrl.text = item.preview_url
            artistAdapter.differ.submitList(item.artists)
            rvTrack.adapter = artistAdapter
            rvTrack.layoutManager = LinearLayoutManager(root.context)
        }
    }
}