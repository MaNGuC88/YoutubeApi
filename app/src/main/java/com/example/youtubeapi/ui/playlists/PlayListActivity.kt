package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistsBinding
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.ui.no_connection.NoConnectionActivity
import com.example.youtubeapi.ui.playlist_detail.PlayListDetailActivity

class PlayListActivity : BaseActivity<PlayListViewModel, ActivityPlaylistsBinding>() {

    private lateinit var adapter: PlayListAdapter
    private lateinit var playList: PlayList

    override fun initView() {
        super.initView()

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initViewModel() {
        super.initViewModel()

        if (isOnline(this)) {
            viewModel.getPlayList().observe(this) {
                playList = it
                createRecyclerView()
            }
        } else {
            openNoConnectionActivity()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun createRecyclerView() {
        adapter = PlayListAdapter(playList, this::passId)
        binding.recyclerPlaylist.adapter = adapter
        binding.recyclerPlaylist.setHasFixedSize(true)
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
                    playList = it
                    createRecyclerView()
                }
            }
        }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun passId(id: String) {
        Intent(this, PlayListDetailActivity::class.java).apply {
            putExtra("id", id)
            startActivity(this)
        }
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.M)
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
            return false
        }
    }
}