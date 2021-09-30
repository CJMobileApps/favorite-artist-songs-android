package com.cjmobileapps.favoriteartistsongsandroid.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cjmobileapps.favoriteartistsongsandroid.databinding.FragmentFavoriteArtistSongsDetailsBinding
import com.squareup.picasso.Picasso

class FavoriteArtistSongsDetailsFragment : Fragment() {

    private val args: FavoriteArtistSongsDetailsFragmentArgs by navArgs()

    private var binding: FragmentFavoriteArtistSongsDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteArtistSongsDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val song = args.song
        binding?.favoriteArtistSongsDetailsSongName?.text = song.trackName
        binding?.favoriteArtistSongsDetailsArtistName?.text = song.artistName
        binding?.favoriteArtistSongsDetailsGenreName?.text = song.primaryGenreName

        Picasso.get()
            .load(song.artworkUrl100)
            .into(binding?.favoriteArtistSongsDetailsImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
