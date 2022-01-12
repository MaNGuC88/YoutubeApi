package com.example.youtubeapi.data.remote

import com.example.youtubeapi.data.remote.models.PlayList
import com.example.youtubeapi.data.remote.models.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("playlists")
    suspend fun getPlaylists (
        @Query ("part") part: String,
        @Query ("channelId") channelId: String,
        @Query ("key") apiKey: String,
        @Query("maxResults") maxResult: Int
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlaylistItems (
        @Query ("part") part: String,
        @Query ("playlistId") id: String,
        @Query ("key") apiKey: String,
        @Query("maxResults") maxResult: Int
    ): Response<PlayList>

    @GET("videos")
    suspend fun getVideo (
        @Query ("part") part: String,
        @Query ("id") id: String,
        @Query ("key") apiKey: String,
        @Query("maxResults") maxResult: Int
    ): Response<Video>
}