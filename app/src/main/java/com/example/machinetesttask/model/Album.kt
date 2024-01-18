package com.example.machinetesttask.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Album(

    val id: String? = null,
    val album_type: String,
    val artists: List<Artist>,
    val copyrights: List<Copyright>,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrlsX,
    val genres: List<Any>,
    val images: List<Image>,
    val albumId:String,
    val is_playable: Boolean,
    val label: String,
    val name: String,
    val popularity: Int,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val tracks: Tracks,
    val type: String,
    val uri: String
)