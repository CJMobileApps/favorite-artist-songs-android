package com.cjmobileapps.favoriteartistsongsandroid.network

import com.cjmobileapps.favoriteartistsongsandroid.data.model.SongResults
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FavoriteArtistSongsApi {

    @GET("search?entity=song&attribute=allArtistTerm")
    fun getSongs(
        @Query("term") artist: String
    ): Single<SongResults>
}
