package com.example.machinetesttask.task1.repo

import com.example.machinetesttask.task1.db.TrackDatabase
import com.example.machinetesttask.task1.model.Albums
import com.example.machinetesttask.task1.model.Item
import com.example.machinetesttask.task1.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(val db: TrackDatabase) {

    suspend fun getApiAlbums() = RetrofitInstance.api.getSpotifyAlbums()

    suspend fun addTrackItems(tracks: List<Item>) = withContext(Dispatchers.IO){
        db.getAlbumsDao().insertItems(tracks = tracks)
    }

    suspend fun getSavedItems(): List<Item> = withContext(Dispatchers.IO){
        db.getAlbumsDao().getItems()
    }
}