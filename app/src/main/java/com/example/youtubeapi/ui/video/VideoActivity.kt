package com.example.youtubeapi.ui.video

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1_lesson2.extensions.showToast
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityVideoBinding
import com.example.youtubeapi.ui.playlist_detail.PlayListDetailViewModel
import com.example.youtubeapi.utils.`object`.Constant
import com.example.youtubeapi.utils.`object`.Constant.VIDEO_ID

class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {

    override fun initView() {
        super.initView()

        viewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        binding.btnBack.setOnClickListener {
            Intent().apply {
                finish()
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        val videoId = intent.getStringExtra(VIDEO_ID).toString()

        showToast(videoId)
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

}