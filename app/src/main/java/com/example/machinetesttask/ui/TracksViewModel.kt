package com.example.machinetesttask.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.machinetesttask.model.Albums
import com.example.machinetesttask.model.Item
import com.example.machinetesttask.repo.Repository
import com.example.machinetesttask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TracksViewModel(
    val repository: Repository
) : ViewModel() {
    val albums: MutableLiveData<Resource<Albums>> = MutableLiveData()
    val items:MutableLiveData<List<Item>> = MutableLiveData()
    init {
        getSongs()
    }

    fun getSongs() = viewModelScope.launch {
        getSongsApiCall()
    }
    fun getSavedItemsfromRepo(): LiveData<List<Item>> {
        return liveData(Dispatchers.IO) {
            emit(repository.getSavedItems())
        }
    }

    private suspend fun getSongsApiCall() {
        albums.postValue(Resource.Loading())
        try {
            val response = repository.getApiAlbums()
            if (response.isSuccessful) {
                response.body().let {it->
                    if (it != null) {
                        for(a in it.albums){
                            repository.addTrackItems(a.tracks.items)
                        }
                    }
                    items.postValue(repository.getSavedItems())
                    albums.postValue(Resource.Sucess(it!!))
                }
            } else {
                albums.postValue(Resource.Error(response.message()))
            }

        } catch (t: Throwable) {
            Log.e("TracksViewModel", "Error during API call", t)
        }
    }
}