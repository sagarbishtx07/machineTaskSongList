package com.example.machinetesttask.task1.model

import androidx.room.TypeConverters
import com.example.machinetesttask.task1.db.Converters

data class ExternalUrlsX(
    @TypeConverters(Converters::class)
    val spotify: String
)