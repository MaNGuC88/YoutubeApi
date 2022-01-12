package com.example.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.data.remote.models.PlayList
import com.example.youtubeapi.repository.Repository

class PlayListViewModel(private val repository: Repository): BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getPlayList(): LiveData<Resource<PlayList>> {
        return repository.getPlayList()
    }
}