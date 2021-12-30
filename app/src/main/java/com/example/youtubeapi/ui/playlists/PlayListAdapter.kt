package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.PlaylistItemBinding
import com.example.youtubeapi.extensions.load
import com.example.youtubeapi.models.Items
import com.example.youtubeapi.models.PlayList

class PlayListAdapter(private val playList: PlayList, private val clickItem: (id: String) -> Unit):
    RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val binding = PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(playList.items[position])
    }

    override fun getItemCount(): Int {
        return playList.items.size
    }

    inner class PlayListViewHolder (itemBinding: PlaylistItemBinding):
        RecyclerView.ViewHolder(itemBinding.root){

        private val binding = itemBinding

        @SuppressLint("SetTextI18n")
        fun bind(items: Items) {
            binding.tvPlaylistTitle.text = items.snippet.title
            binding.tvPlaylistQty.text =
                "${items.contentDetails.itemCount} video series"
            binding.ivPlaylist.load(items.snippet.thumbnails.default.url)

            itemView.setOnClickListener {
                clickItem(items.id)
            }

        }
    }
}