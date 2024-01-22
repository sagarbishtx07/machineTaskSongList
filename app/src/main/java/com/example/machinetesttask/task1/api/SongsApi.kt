package com.example.machinetesttask.task1.api

import com.example.machinetesttask.task1.model.Album
import com.example.machinetesttask.task1.model.Albums
import com.example.machinetesttask.task1.utils.MyConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SongsApi {

    @GET("spotify/albums")
    suspend fun getSpotifyAlbums(
        @Header("apikey") apiKey: String = MyConstants.API_KEY,
        @Query("ids") albumId: String = MyConstants.ALBUM_IDS
    ):Response<Albums>
}