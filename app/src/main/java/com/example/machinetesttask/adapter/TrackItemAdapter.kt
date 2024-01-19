package com.example.machinetesttask.adapter

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetesttask.databinding.ItemSongLayoutBinding
import com.example.machinetesttask.model.Item
import java.io.IOException

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
    private var mediaPlayer: MediaPlayer? = null
    val differ = AsyncListDiffer(this, differCallback)
    private var currentPlayingPosition: Int? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding =
            ItemSongLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, @SuppressLint("RecyclerView") position: Int) {
        mediaPlayer?.release()
        mediaPlayer = null
        val holderBinding = holder.binding
        val item = differ.currentList[position]
        val artistAdapter = ArtistAdapter()
        holderBinding.apply {

            tvName.text = item.name
            artistAdapter.differ.submitList(item.artists)
            rvTrack.adapter = artistAdapter
            rvTrack.layoutManager = LinearLayoutManager(root.context)
            play.setOnClickListener {
                currentPlayingPosition?.let { oldPosition ->
                    if (oldPosition != position) {
                        stopMediaPlayer()
                    }
                }

                play.visibility = View.GONE
                pause.visibility = View.VISIBLE
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer()

                    try {
                        mediaPlayer?.setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build()
                        )
                        mediaPlayer?.setDataSource(item.preview_url)
                        mediaPlayer?.prepare()
                        mediaPlayer?.start()
                        currentPlayingPosition = position
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    mediaPlayer?.start()
                    currentPlayingPosition = position
                }
            }
            pause.setOnClickListener {
                play.visibility = View.VISIBLE
                pause.visibility = View.GONE
               stopMediaPlayer()
            }
        }
    }
    private fun stopMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
        currentPlayingPosition = null
    }
}