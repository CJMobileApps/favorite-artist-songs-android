package com.cjmobileapps.favoriteartistsongsandroid.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cjmobileapps.favoriteartistsongsandroid.data.model.Song
import com.cjmobileapps.favoriteartistsongsandroid.databinding.ItemFavoriteArtistSongBinding
import com.squareup.picasso.Picasso

class FavoriteArtistSongsListAdapter(var songs: List<Song> = emptyList()): RecyclerView.Adapter<FavoriteArtistSongsListAdapter.FavoriteArtistSongsListHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteArtistSongsListHolder {
        val itemBinding = ItemFavoriteArtistSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteArtistSongsListHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteArtistSongsListHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount() = songs.size

    inner class FavoriteArtistSongsListHolder(private val itemBinding: ItemFavoriteArtistSongBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(song: Song) {
            itemBinding.favoriteArtistSongSongName.text = song.trackName
            itemBinding.favoriteArtistSongArtistName.text = song.artistName

            Picasso.get()
                .load(song.artworkUrl100)
                .into(itemBinding.favoriteArtistSongImage)

            itemBinding.root.setOnClickListener { view ->
                val action = FavoriteArtistSongsListFragmentDirections.actionFavoriteArtistSongsListToFavoriteArtistSongsDetails(song)
                view.findNavController().navigate(action)
            }
        }
    }
}
