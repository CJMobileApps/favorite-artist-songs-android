package com.cjmobileapps.favoriteartistsongsandroid.dagger.module

import com.cjmobileapps.favoriteartistsongsandroid.dagger.FavoriteArtistSongsApplicationScope
import com.cjmobileapps.favoriteartistsongsandroid.data.FavoriteArtistSongsService
import com.cjmobileapps.favoriteartistsongsandroid.network.FavoriteArtistSongsApi
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun favoriteArtistSongsService(
        favoriteArtistSongsApi: FavoriteArtistSongsApi
    ): FavoriteArtistSongsService {
        return FavoriteArtistSongsService(favoriteArtistSongsApi)
    }
}
