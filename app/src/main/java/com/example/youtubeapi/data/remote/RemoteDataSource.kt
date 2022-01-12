package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.network.BaseDataSource
import com.example.youtubeapi.utils.`object`.Constant
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val youtubeApi: YoutubeApi) : BaseDataSource() {

    // suspend stops and resumes thread when necessary
    suspend fun getPlaylist() = getResult {
        youtubeApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 20)
    }

    suspend fun getPlayListItems(id: String) = getResult {
        youtubeApi.getPlaylistItems(Constant.PART, id, BuildConfig.API_KEY, 40)
    }

    suspend fun getVideo(id: String) = getResult {
        youtubeApi.getVideo(Constant.PART, id, BuildConfig.API_KEY, 1)
    }

}