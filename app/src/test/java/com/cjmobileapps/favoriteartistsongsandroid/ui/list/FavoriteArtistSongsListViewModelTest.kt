package com.cjmobileapps.favoriteartistsongsandroid.ui.list

import com.cjmobileapps.favoriteartistsongsandroid.BaseTest
import com.cjmobileapps.favoriteartistsongsandroid.data.FakeData
import com.cjmobileapps.favoriteartistsongsandroid.data.FavoriteArtistSongsService
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

class FavoriteArtistSongsListViewModelTest : BaseTest() {

    @Mock
    lateinit var mockFavoriteArtistSongsService: FavoriteArtistSongsService

    private lateinit var favoriteArtistSongsListViewModel: FavoriteArtistSongsListViewModel

    private val mockSongResult = FakeData.songsResults

    @Before
    fun setUpFavoriteArtistSongsListViewModel() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getSongs_returnGetSongs_Success() {

        // given
        val artist = "Drake"

        // when
        whenever(mockFavoriteArtistSongsService.getSongs(artist)).thenReturn(
            Single.just(
                mockSongResult
            )
        )

        // then
        this.favoriteArtistSongsListViewModel =
            FavoriteArtistSongsListViewModel(mockFavoriteArtistSongsService)
        favoriteArtistSongsListViewModel.getSongs(artist)

        // verify
        assertEquals(mockSongResult.results, favoriteArtistSongsListViewModel.songsLiveData.value)
        assertEquals(false, favoriteArtistSongsListViewModel.songsAreLoading.value)
    }

    @Test
    fun getSongs_getSongsThrowError_causingFailure() {

        // given
        val artist = "Drake"

        // when
        whenever(mockFavoriteArtistSongsService.getSongs(artist)).thenReturn(
            Single.error(Exception("exception"))
        )

        // then
        this.favoriteArtistSongsListViewModel =
            FavoriteArtistSongsListViewModel(mockFavoriteArtistSongsService)
        favoriteArtistSongsListViewModel.getSongs(artist)

        // verify
        assertEquals(null, favoriteArtistSongsListViewModel.songsLiveData.value)
        assertEquals(false, favoriteArtistSongsListViewModel.songsAreLoading.value)
    }
}
