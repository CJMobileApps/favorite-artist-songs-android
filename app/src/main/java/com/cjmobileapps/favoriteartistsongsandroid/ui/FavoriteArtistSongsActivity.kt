package com.cjmobileapps.favoriteartistsongsandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cjmobileapps.favoriteartistsongsandroid.databinding.ActivityFavoriteArtistSongsBinding

class FavoriteArtistSongsActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavoriteArtistSongsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteArtistSongsBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }
}
