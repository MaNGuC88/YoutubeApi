package com.example.youtubeapi.ui.video

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import org.koin.androidx.viewmodel.ext.android.viewModel
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YtFile
import android.util.SparseArray
import at.huber.youtubeExtractor.YouTubeExtractor
import com.example.youtubeapi.BuildConfig.YOUTUBE_URL
import com.google.android.exoplayer2.source.MergingMediaSource

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("StaticFieldLeak")
class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {

    private lateinit var videoURL: String
    private var mPlayer: ExoPlayer? = null
    private lateinit var videoSource: ProgressiveMediaSource
    private lateinit var audioSource: ProgressiveMediaSource

    override val viewModel: VideoViewModel by viewModel()

    override fun initView() {
        super.initView()

        videoURL = YOUTUBE_URL + intent.getStringExtra(VIDEO_ID).toString()

        downloadVideo()

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

    private fun downloadVideo() {
        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
                if (ytFiles != null) {
                    val videoTag = 134
                    val audioTag = 140
                    val videoUrl = ytFiles[videoTag].url
                    val audioUrl = ytFiles[audioTag].url
                    initializePlayer(videoUrl, audioUrl)
                }
            }
        }.extract(videoURL)
    }

    private fun initializePlayer(videoUrl: String, audioUrl: String) {
        buildMediaSource(videoUrl, audioUrl)

        mPlayer = ExoPlayer.Builder(this).build()
        binding.exoplayer.player = mPlayer
        mPlayer!!.playWhenReady = true
        mPlayer!!.setMediaSource(MergingMediaSource(videoSource, audioSource))
        mPlayer!!.prepare()
    }

    private fun buildMediaSource(videoUrl: String, audioUrl: String) {
        videoSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(videoUrl))
        audioSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(audioUrl))
    }

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

    public override fun onPause() {
        super.onPause()
        mPlayer?.pause()
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }
}