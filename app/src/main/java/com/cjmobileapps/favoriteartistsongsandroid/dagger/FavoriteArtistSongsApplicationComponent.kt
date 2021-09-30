package com.cjmobileapps.favoriteartistsongsandroid.dagger

import com.cjmobileapps.favoriteartistsongsandroid.FavoriteArtistSongsApplication
import com.cjmobileapps.favoriteartistsongsandroid.dagger.module.ContextModule
import com.cjmobileapps.favoriteartistsongsandroid.dagger.module.DataModule
import com.cjmobileapps.favoriteartistsongsandroid.dagger.module.NetworkModule
import com.cjmobileapps.favoriteartistsongsandroid.data.FavoriteArtistSongsService
import dagger.Component

@FavoriteArtistSongsApplicationScope
@Component(modules = [ContextModule::class, NetworkModule::class, DataModule::class])
interface FavoriteArtistSongsApplicationComponent {

    fun provideFavoriteArtistSongsService(): FavoriteArtistSongsService

    fun injectFavoriteArtistSongsApplicationComponent(favoriteArtistSongsApplication: FavoriteArtistSongsApplication)
}
