package com.example.youtubeapi.ui.playlist_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.data.remote.models.Items
import com.example.youtubeapi.databinding.ListItemBinding
import com.example.youtubeapi.extensions.load

class PlayListItemsAdapter(private val list: List<Items>,
                           private val clickListener: (videoId: String) -> Unit):
RecyclerView.Adapter<PlayListItemsAdapter.PlayListItemsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListItemsViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListItemsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListItemsViewHolder(private val itemBinding: ListItemBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Items) {
            itemBinding.ivPlItem.load(item.snippet.thumbnails.medium.url)
            itemBinding.tvPlItemTitle.text = item.snippet.title

            itemBinding.root.setOnClickListener {
                clickListener(item.contentDetails.videoId)
            }
        }
    }
}