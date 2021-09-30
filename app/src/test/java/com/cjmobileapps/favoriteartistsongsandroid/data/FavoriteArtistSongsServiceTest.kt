package com.cjmobileapps.favoriteartistsongsandroid.data

import com.cjmobileapps.favoriteartistsongsandroid.BaseTest
import com.cjmobileapps.favoriteartistsongsandroid.network.FavoriteArtistSongsApi
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class FavoriteArtistSongsServiceTest : BaseTest() {

    @Mock
    lateinit var mockFavoriteArtistSongsApi: FavoriteArtistSongsApi

    private lateinit var service: FavoriteArtistSongsService

    private val mockSongResults = FakeData.songsResults

    @Before
    fun setUpFavoriteArtistSongsService() {
        this.service = FavoriteArtistSongsService(
            favoriteArtistSongsApi = mockFavoriteArtistSongsApi
        )
    }

    @Test
    fun getSongs_whenGetSongsCalled() {

        // given
        val artist = "Drake"

        // when
        whenever(
            mockFavoriteArtistSongsApi.getSongs(artist)
        ).thenReturn(Single.just(mockSongResults))

        // then
        val songResults = service.getSongs(artist)

        // verify
        assertEquals(
            songResults.blockingGet(),
            mockSongResults
        )
    }
}
