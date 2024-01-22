package com.example.machinetesttask.task1.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class Albums(
    @SerializedName("albums")
    val albums: List<Album>
)
