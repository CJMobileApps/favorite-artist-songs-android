package com.cjmobileapps.favoriteartistsongsandroid

import android.app.Activity
import android.app.Application
import android.content.Context
import com.cjmobileapps.favoriteartistsongsandroid.dagger.DaggerFavoriteArtistSongsApplicationComponent
import com.cjmobileapps.favoriteartistsongsandroid.dagger.FavoriteArtistSongsApplicationComponent
import com.cjmobileapps.favoriteartistsongsandroid.dagger.module.ContextModule
import timber.log.Timber

class FavoriteArtistSongsApplication : Application() {

    lateinit var favoriteArtistApplicationComponent: FavoriteArtistSongsApplicationComponent

    companion object {

        fun get(activity: Activity): FavoriteArtistSongsApplication {
            return activity.application as FavoriteArtistSongsApplication
        }

        fun get(context: Context): FavoriteArtistSongsApplication {
            return context.applicationContext as FavoriteArtistSongsApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        favoriteArtistApplicationComponent = DaggerFavoriteArtistSongsApplicationComponent.builder()
            .contextModule(ContextModule(this)).build()

        favoriteArtistApplicationComponent.injectFavoriteArtistSongsApplicationComponent(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
