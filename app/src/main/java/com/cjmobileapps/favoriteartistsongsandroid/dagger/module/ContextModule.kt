package com.cjmobileapps.favoriteartistsongsandroid.dagger.module

import android.content.Context
import com.cjmobileapps.favoriteartistsongsandroid.dagger.FavoriteArtistSongsApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context) {
    private val context = context.applicationContext

    @FavoriteArtistSongsApplicationScope
    @Provides
     fun context(): Context {
         return context
     }
}
