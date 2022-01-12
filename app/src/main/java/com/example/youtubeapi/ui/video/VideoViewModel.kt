package com.example.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.data.remote.models.Video
import com.example.youtubeapi.repository.Repository

class VideoViewModel(private val repository: Repository): BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getVideo(id: String): LiveData<Resource<Video>> {
        return repository.getVideo(id)
    }

}