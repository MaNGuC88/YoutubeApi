package com.example.youtubeapi.ui.playlist_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.data.remote.models.PlayList
import com.example.youtubeapi.repository.Repository

class PlayListItemsViewModel(private val repository: Repository): BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getPlayListItems(id: String): LiveData<Resource<PlayList>> {
        return repository.getPlayListItems(id)
    }

}