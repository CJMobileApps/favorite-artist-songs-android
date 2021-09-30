package com.cjmobileapps.favoriteartistsongsandroid.dagger.module

import android.content.Context
import com.cjmobileapps.favoriteartistsongsandroid.BuildConfig
import com.cjmobileapps.favoriteartistsongsandroid.dagger.FavoriteArtistSongsApplicationScope
import com.cjmobileapps.favoriteartistsongsandroid.network.FavoriteArtistSongsApi
import com.cjmobileapps.favoriteartistsongsandroid.network.NetworkConstants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun httpCacheDirectory(context: Context): File {
        return File(context.cacheDir, NetworkConstants.HTTP_CACHE_DIR)
    }

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun cache(httpCacheDirectory: File): Cache {
        return Cache(httpCacheDirectory, NetworkConstants.HTTP_CACHE_SIZE)
    }

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun networkCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()

            response.newBuilder()
                .header(NetworkConstants.CACHE_CONTROL, cacheControl.toString()).build()
        }
    }

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        return loggingInterceptor
    }

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun okHttpClient(
        cache: Cache,
        networkCacheInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkCacheInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @FavoriteArtistSongsApplicationScope
    @Provides
    fun favoriteArtistSongsPicApi(retrofit: Retrofit): FavoriteArtistSongsApi {
        return retrofit.create(FavoriteArtistSongsApi::class.java)
    }
}
