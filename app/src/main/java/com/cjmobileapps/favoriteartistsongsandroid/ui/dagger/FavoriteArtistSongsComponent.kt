package com.cjmobileapps.favoriteartistsongsandroid.ui.dagger

import com.cjmobileapps.favoriteartistsongsandroid.dagger.FavoriteArtistSongsApplicationComponent
import com.cjmobileapps.favoriteartistsongsandroid.data.FavoriteArtistSongsService
import com.cjmobileapps.favoriteartistsongsandroid.ui.list.FavoriteArtistSongsListFragment
import dagger.Component

@FavoriteArtistSongsScope
@Component(dependencies = [FavoriteArtistSongsApplicationComponent::class])
interface FavoriteArtistSongsComponent {

    fun provideFavoriteArtistSongsService(): FavoriteArtistSongsService

    fun inject(fragment: FavoriteArtistSongsListFragment)
}
