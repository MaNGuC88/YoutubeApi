package com.example.youtubeapi.di

import com.example.youtubeapi.core.network.networkModule
import com.example.youtubeapi.data.local.prefsModule
import com.example.youtubeapi.data.remote.remoteDataSource

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModule,
    remoteDataSource,
    prefsModule
)