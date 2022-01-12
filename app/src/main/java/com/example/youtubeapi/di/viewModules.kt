package com.example.youtubeapi.di

import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.ui.playlist_items.PlayListItemsViewModel
import com.example.youtubeapi.ui.playlists.PlayListViewModel
import com.example.youtubeapi.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlayListViewModel(get()) }
    viewModel { PlayListItemsViewModel(get()) }
    viewModel { VideoViewModel(get()) }
    viewModel { BaseViewModel() }
}