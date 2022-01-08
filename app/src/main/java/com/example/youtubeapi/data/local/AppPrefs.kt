package com.example.youtubeapi.data.local

import android.content.Context

class AppPrefs(context: Context) {

    private val prefs = context.getSharedPreferences("youtube_api", Context.MODE_PRIVATE)

}