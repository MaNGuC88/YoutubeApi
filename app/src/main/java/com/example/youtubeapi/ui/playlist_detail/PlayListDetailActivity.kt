package com.example.youtubeapi.ui.playlist_detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin1_lesson2.extensions.showToast
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.data.remote.models.Items
import com.example.youtubeapi.databinding.ActivityPlayListDetailBinding
import com.example.youtubeapi.ui.no_connection.NoConnectionActivity
import com.example.youtubeapi.ui.video.VideoActivity
import com.example.youtubeapi.utils.`object`.Constant.PLAYLIST_DESC
import com.example.youtubeapi.utils.`object`.Constant.PLAYLIST_ID
import com.example.youtubeapi.utils.`object`.Constant.PLAYLIST_TITLE
import com.example.youtubeapi.utils.`object`.Constant.VIDEO_ID

class PlayListDetailActivity :
    BaseActivity<PlayListDetailViewModel, ActivityPlayListDetailBinding>() {

    private var list = listOf<Items>()
    private val adapter: PlayListDetailAdapter by lazy {
        PlayListDetailAdapter(list, this::clickListener)
    }

    override fun initView() {
        super.initView()

        viewModel = ViewModelProvider(this).get(PlayListDetailViewModel::class.java)

        binding.btnBack.setOnClickListener {
            Intent().apply {
                finish()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initViewModel() {
        super.initViewModel()
        val id = intent.getStringExtra(PLAYLIST_ID).toString()
        val title = intent.getStringExtra(PLAYLIST_TITLE).toString()
        val description = intent.getStringExtra(PLAYLIST_DESC).toString()

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        if (isOnline(this)) {
            viewModel.getPlayListItems(id).observe(this) {
                when (it.status) {
                    Status.LOADING -> viewModel.loading.postValue(true)
                    Status.SUCCESS -> {
                        viewModel.loading.postValue(false)
                        initAdapter(it.data?.items as List<Items>)
                    }
                    Status.ERROR -> {
                        viewModel.loading.postValue(false)
                        showToast(it.message.toString())
                    }
                }
            }
        } else {
            openNoConnectionActivity()
        }

        binding.tvPlTitle.text = title
        binding.tvPlDescription.text = description
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun initAdapter(list: List<Items>) {
        this.list = list
        binding.recyclerVideo.apply {
            layoutManager = LinearLayoutManager(this@PlayListDetailActivity)
            adapter = this@PlayListDetailActivity.adapter
        }
        adapter.notifyDataSetChanged()
        binding.tvPlVideosQty.text = "${list.size} video series"

        binding.fab.setOnClickListener {
            clickListener(list[0].contentDetails.videoId)
        }
    }

    private fun openNoConnectionActivity() {
        Intent(this, NoConnectionActivity::class.java).apply {
            resultLauncher.launch(this)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val id = intent.getStringExtra(PLAYLIST_ID).toString()
                viewModel.getPlayListItems(id).observe(this) {
                    when (it.status) {
                        Status.LOADING -> viewModel.loading.postValue(true)
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(false)
                            initAdapter(it.data?.items as List<Items>)
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                            showToast(it.message.toString())
                        }
                    }
                }
            }
        }

    private fun clickListener(videoId: String) {
        Intent(this, VideoActivity::class.java).apply {
            putExtra(VIDEO_ID, videoId)
            startActivity(this)
        }
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlayListDetailBinding {
        return ActivityPlayListDetailBinding.inflate(layoutInflater)
    }

}