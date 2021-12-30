package com.example.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.BuildConfig.API_KEY
import com.example.youtubeapi.`object`.Constant
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel: BaseViewModel() {

    private val youtubeApi = RetrofitClient.create()

    fun getPlayList(): LiveData<PlayList> {
        return createPlayList()
    }

    private fun createPlayList(): LiveData<PlayList> {

        val data = MutableLiveData<PlayList>()

        youtubeApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, API_KEY)
            .enqueue(object: Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    print(t.stackTrace)
                }
            })
        return data
    }
}