package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.PlaylistItemBinding
import com.example.youtubeapi.extensions.load
import com.example.youtubeapi.data.remote.models.Items

class PlayListAdapter(private val list: List<Items>, private val clickListener: (item: Items) -> Unit):
    RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val binding = PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListViewHolder (itemBinding: PlaylistItemBinding):
        RecyclerView.ViewHolder(itemBinding.root){

        private val binding = itemBinding

        @SuppressLint("SetTextI18n")
        fun bind(item: Items) {
            binding.tvPlaylistTitle.text = item.snippet.title
            binding.tvPlaylistQty.text =
                "${item.contentDetails.itemCount} video series"
            binding.ivPlaylist.load(item.snippet.thumbnails.medium.url)

            binding.root.setOnClickListener {
                clickListener(item)
            }
        }
    }
}