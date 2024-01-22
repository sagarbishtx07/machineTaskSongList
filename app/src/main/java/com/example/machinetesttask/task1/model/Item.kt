package com.example.machinetesttask.task1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.machinetesttask.task1.db.Converters
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "items"
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    val localId:Int?=null,
    @TypeConverters(Converters::class)
    val artists: List<Artist>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    @TypeConverters(Converters::class)
    val external_urls: ExternalUrlsX,
    @SerializedName("id")
    val song_id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val name: String,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String
)