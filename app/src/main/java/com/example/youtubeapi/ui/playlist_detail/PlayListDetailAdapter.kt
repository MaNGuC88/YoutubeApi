package com.example.youtubeapi.ui.playlist_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.data.remote.models.Items
import com.example.youtubeapi.databinding.ActivityPlayListDetailBinding
import com.example.youtubeapi.databinding.ListItemBinding
import com.example.youtubeapi.extensions.load

class PlayListDetailAdapter(private val list: List<Items>,
                            private val clickListener: (videoId: String) -> Unit):
RecyclerView.Adapter<PlayListDetailAdapter.PlayListDetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListDetailViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListDetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListDetailViewHolder(private val itemBinding: ListItemBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(videoItem: Items) {
            itemBinding.ivPlItem.load(videoItem.snippet.thumbnails.medium.url)
            itemBinding.tvPlItemTitle.text = videoItem.snippet.title

            itemBinding.root.setOnClickListener {
                clickListener(videoItem.contentDetails.videoId)
            }
        }
    }
}