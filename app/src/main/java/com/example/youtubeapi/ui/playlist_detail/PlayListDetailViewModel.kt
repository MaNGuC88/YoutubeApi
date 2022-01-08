package com.example.youtubeapi.ui.playlist_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.data.remote.models.PlayList

class PlayListDetailViewModel: BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getPlayListItems(id: String): LiveData<Resource<PlayList>> {
        return App().repository.createPlayListItems(id)
    }

}