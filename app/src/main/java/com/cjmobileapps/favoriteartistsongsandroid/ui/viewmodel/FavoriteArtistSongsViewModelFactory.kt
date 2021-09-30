package com.cjmobileapps.favoriteartistsongsandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cjmobileapps.favoriteartistsongsandroid.data.FavoriteArtistSongsService
import com.cjmobileapps.favoriteartistsongsandroid.ui.list.FavoriteArtistSongsListViewModel

class FavoriteArtistSongsViewModelFactory(
   private val favoriteArtistSongsService: FavoriteArtistSongsService
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteArtistSongsListViewModel::class.java) -> {
                FavoriteArtistSongsListViewModel(favoriteArtistSongsService) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}
