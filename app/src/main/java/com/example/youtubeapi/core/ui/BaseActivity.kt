package com.example.youtubeapi.core.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM: BaseViewModel,VB: ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    protected abstract fun inflateVB(inflater: LayoutInflater) :VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateVB(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
        initListener()
    }

    open fun initViewModel() {}
    open fun initListener() {}
    open fun initView() {}

    @RequiresApi(Build.VERSION_CODES.M)
    open fun isOnline(context: Context): Boolean {
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