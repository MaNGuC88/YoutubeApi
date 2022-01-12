package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin1_lesson2.extensions.showToast
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistsBinding
import com.example.youtubeapi.data.remote.models.Items
import com.example.youtubeapi.ui.no_connection.NoConnectionActivity
import com.example.youtubeapi.ui.playlist_items.PlayListItemsActivity
import com.example.youtubeapi.utils.`object`.Constant.PLAYLIST_DESC
import com.example.youtubeapi.utils.`object`.Constant.PLAYLIST_ID
import com.example.youtubeapi.utils.`object`.Constant.PLAYLIST_TITLE
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListActivity : BaseActivity<PlayListViewModel, ActivityPlaylistsBinding>() {

    private var items = listOf<Items>()
    private val adapter: PlayListAdapter by lazy {
        PlayListAdapter(items, this::clickListener)
    }

    override val viewModel: PlayListViewModel by viewModel()

    override fun initView() {
        super.initView()
        supportActionBar?.hide()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initViewModel() {
        super.initViewModel()

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        if (isOnline(this)) {
            viewModel.getPlayList().observe(this) {
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
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAdapter(items: List<Items>) {
        this.items = items
        binding.recyclerPlaylist.apply {
            layoutManager = LinearLayoutManager(this@PlayListActivity)
            adapter = this@PlayListActivity.adapter
        }
        adapter.notifyDataSetChanged()
    }

    private fun openNoConnectionActivity() {
        Intent(this, NoConnectionActivity::class.java).apply {
            resultLauncher.launch(this)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.getPlayList().observe(this) {
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

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun clickListener(items: Items) {
        Intent(this, PlayListItemsActivity::class.java).apply {
            putExtra(PLAYLIST_ID, items.id)
            putExtra(PLAYLIST_TITLE, items.snippet.title)
            putExtra(PLAYLIST_TITLE, items.snippet.title)
            putExtra(PLAYLIST_DESC, items.snippet.description)
            startActivity(this)
        }
    }

}