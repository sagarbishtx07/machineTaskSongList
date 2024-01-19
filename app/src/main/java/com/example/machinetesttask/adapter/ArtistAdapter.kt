package com.example.machinetesttask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetesttask.R
import com.example.machinetesttask.databinding.ItemArtistLayoutBinding
import com.example.machinetesttask.model.Artist

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {
    inner class ArtistViewHolder(val binding: ItemArtistLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding:ItemArtistLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_artist_layout,parent,false)
        return ArtistViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            artistitem = item
        }
    }
}