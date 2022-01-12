package com.example.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.data.remote.RemoteDataSource
import com.example.youtubeapi.data.remote.models.PlayList
import com.example.youtubeapi.data.remote.models.Video
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

    fun getPlayList(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading()) // emit observes thread and returns data
        val response = dataSource.getPlaylist()
        emit(response)
    }

    fun getPlayListItems(id: String): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getPlayListItems(id)
        emit(response)
    }

    fun getVideo(id: String): LiveData<Resource<Video>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getVideo(id)
        emit(response)
    }

}