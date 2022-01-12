package com.example.youtubeapi.data.local

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefsModule = module {
    single { AppPrefs(androidContext()) }
}

class AppPrefs(context: Context) {

    private val prefs = context.getSharedPreferences("youtube_api", Context.MODE_PRIVATE)

}