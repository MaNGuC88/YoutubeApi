package com.example.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.App
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.utils.`object`.Constant
import com.example.youtubeapi.data.remote.models.PlayList
import kotlinx.coroutines.Dispatchers

class Repository {

    fun createPlayList(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = App().youtubeApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 20)
        if (response.isSuccessful && response.body() != null) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), response.body(), response.code()))
        }
    }

    fun createPlayListItems(id: String): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = App().youtubeApi.getPlaylistItems(Constant.PART, id, BuildConfig.API_KEY, 40)
        if (response.isSuccessful && response.body() != null) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), response.body(), response.code()))
        }
    }

}