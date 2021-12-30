package com.example.youtubeapi.ui.playlist_detail

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1_lesson2.extensions.showToast
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlayListDetailBinding

class PlayListDetailActivity : BaseActivity<PlayListDetailViewModel, ActivityPlayListDetailBinding>() {

    override fun initView() {
        super.initView()

        viewModel = ViewModelProvider(this).get(PlayListDetailViewModel::class.java)
    }

    override fun initViewModel() {
        super.initViewModel()

        val id = intent.getStringExtra("id").toString()
        showToast(id)

    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlayListDetailBinding {
        return ActivityPlayListDetailBinding.inflate(layoutInflater)
    }

}