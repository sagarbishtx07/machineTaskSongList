package com.example.machinetesttask.model

import androidx.room.TypeConverters
import com.example.machinetesttask.db.Converters

data class ExternalUrlsX(
    @TypeConverters(Converters::class)
    val spotify: String
)