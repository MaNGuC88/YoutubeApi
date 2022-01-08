package com.example.youtubeapi

import android.app.Application
import com.example.youtubeapi.core.network.RetrofitClient
import com.example.youtubeapi.repository.Repository

class App: Application() {

    val youtubeApi by lazy { RetrofitClient.create() }

    val repository by lazy { Repository() }

}