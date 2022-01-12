package com.example.youtubeapi.ui.video

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import com.example.kotlin1_lesson2.extensions.showToast
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.data.remote.models.Video
import com.example.youtubeapi.databinding.ActivityVideoBinding
import com.example.youtubeapi.ui.no_connection.NoConnectionActivity
import com.example.youtubeapi.utils.`object`.Constant.VIDEO_ID
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {

    private var player: ExoPlayer? = null
    private var playWhenReady = false
    private var currentWindow = 0
    private var playbackPosition = 0L

    override val viewModel: VideoViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        super.initView()

        binding.btnBack.setOnClickListener {
            if (isOnline(this)) {
                Intent().apply {
                    finish()
                }
            } else {
                openNoConnectionActivity()
            }
        }
    }

    @SuppressLint("NewApi")
    override fun initViewModel() {
        super.initViewModel()

        val videoId = intent.getStringExtra(VIDEO_ID).toString()

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        if (isOnline(this)) {
            viewModel.getVideo(videoId).observe(this) {
                when (it.status) {
                    Status.LOADING -> viewModel.loading.postValue(true)
                    Status.SUCCESS -> {
                        viewModel.loading.postValue(false)
                        initVideoActivityViews(it)
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
    }

    private fun initVideoActivityViews(resource: Resource<Video>) {
        initializePlayer(resource)
        binding.tvVideoTitle.text = resource.data?.items?.get(0)?.snippet?.title
        binding.tvVideoDescription.text = resource.data?.items?.get(0)?.snippet?.description
    }

    private fun openNoConnectionActivity() {
        Intent(this, NoConnectionActivity::class.java).apply {
            resultLauncher.launch(this)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val videoId = intent.getStringExtra(VIDEO_ID).toString()
                viewModel.getVideo(videoId).observe(this) {
                    when (it.status) {
                        Status.LOADING -> viewModel.loading.postValue(true)
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(false)
                            initVideoActivityViews(it)
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                            showToast(it.message.toString())
                        }
                    }
                }
            }
        }

    private fun initializePlayer(resource: Resource<Video>) {
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.exoplayer.player = exoPlayer
            }
            .also { exoPlayer ->
                val mediaItem =
                    MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.prepare()
            }

    }

    public override fun onStart() {
        super.onStart()
        val videoId = intent.getStringExtra(VIDEO_ID).toString()
        if (Util.SDK_INT >= 24) {
            viewModel.getVideo(videoId).observe(this) {
                when (it.status) {
                    Status.LOADING -> viewModel.loading.postValue(true)
                    Status.SUCCESS -> {
                        viewModel.loading.postValue(false)
                        initializePlayer(it)
                    }
                    Status.ERROR -> {
                        viewModel.loading.postValue(false)
                        showToast(it.message.toString())
                    }
                }
            }
        }
    }

    public override fun onResume() {
        super.onResume()
        val videoId = intent.getStringExtra(VIDEO_ID).toString()
        hideSystemUi()
        if ((Util.SDK_INT < 24 || player == null)) {
            viewModel.getVideo(videoId).observe(this) {
                when (it.status) {
                    Status.LOADING -> viewModel.loading.postValue(true)
                    Status.SUCCESS -> {
                        viewModel.loading.postValue(false)
                        initializePlayer(it)
                    }
                    Status.ERROR -> {
                        viewModel.loading.postValue(false)
                        showToast(it.message.toString())
                    }
                }
            }
        }
    }

    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.container).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
            player?.pause()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

}