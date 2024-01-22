package com.example.machinetesttask.task1.db

import androidx.room.TypeConverter
import com.example.machinetesttask.task1.model.Artist
import com.example.machinetesttask.task1.model.ExternalUrlsX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromExternalUrlsX(externalUrlsX: ExternalUrlsX): String {
        return externalUrlsX.spotify
    }

    @TypeConverter
    fun toExternalUrlx(externalUrlsX: String): ExternalUrlsX {
        return ExternalUrlsX(externalUrlsX)
    }

    @TypeConverter
    fun fromArtists(artists: List<Artist>): String {
        val gson = Gson()
        return gson.toJson(artists)
    }

    @TypeConverter
    fun toArtists(artistsJson: String): List<Artist> {
        val gson = Gson()
        val type = object : TypeToken<List<Artist>>() {}.type
        return gson.fromJson(artistsJson, type)
    }
}