package com.example.youtubeapi.ui.no_connection

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.databinding.ActivityNoConnectionBinding
import org.koin.android.ext.android.inject

class NoConnectionActivity : BaseActivity<BaseViewModel, ActivityNoConnectionBinding>() {

    override val viewModel: BaseViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initListener() {
        super.initListener()

        binding.btnTryAgain.setOnClickListener {
            if (isOnline(this)) {
                returnToPlayListActivity()
            } else {
                return@setOnClickListener
            }
        }
    }

    private fun returnToPlayListActivity() {
        Intent().apply {
            finish()
        }
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityNoConnectionBinding {
        return ActivityNoConnectionBinding.inflate(layoutInflater)
    }
}