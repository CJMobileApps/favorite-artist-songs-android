package com.cjmobileapps.favoriteartistsongsandroid.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cjmobileapps.favoriteartistsongsandroid.data.FavoriteArtistSongsService
import com.cjmobileapps.favoriteartistsongsandroid.data.model.Song
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FavoriteArtistSongsListViewModel(
    private val favoriteArtistSongsService: FavoriteArtistSongsService
): ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val songsMutableLiveData = MutableLiveData<List<Song>>()
    val songsLiveData = songsMutableLiveData

    private val songsAreLoadingMutableLiveData = MutableLiveData<Boolean>(false)
    val songsAreLoading = songsAreLoadingMutableLiveData

    private val showDefaultErrorMessageMutableLiveData = MutableLiveData<Boolean>()
    val showDefaultErrorMessageLiveData = showDefaultErrorMessageMutableLiveData

    private val tag = FavoriteArtistSongsListViewModel::class.java.simpleName

    fun getSongs(artist: String) {
        compositeDisposable.add(getSongsDisposable(artist))
    }

    private fun getSongsDisposable(artist: String): Disposable {

        songsAreLoadingMutableLiveData.value = true
        return favoriteArtistSongsService.getSongs(
            artist
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ songResults ->
                songsMutableLiveData.value = songResults.results
                songsAreLoadingMutableLiveData.value = false
            }, { error ->
                Timber.tag(tag).e("getSongs() error message ${error.message}")
                showDefaultErrorMessageLiveData.value = true
                showDefaultErrorMessageLiveData.value = false
                songsAreLoadingMutableLiveData.value = false
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
