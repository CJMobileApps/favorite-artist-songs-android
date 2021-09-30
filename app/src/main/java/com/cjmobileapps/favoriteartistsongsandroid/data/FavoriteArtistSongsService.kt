package com.cjmobileapps.favoriteartistsongsandroid.data

import com.cjmobileapps.favoriteartistsongsandroid.network.FavoriteArtistSongsApi

class FavoriteArtistSongsService(private val favoriteArtistSongsApi: FavoriteArtistSongsApi) {

    fun getSongs(artist: String) = favoriteArtistSongsApi.getSongs(artist)
}
