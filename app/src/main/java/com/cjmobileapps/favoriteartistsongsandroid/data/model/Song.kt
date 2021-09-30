package com.cjmobileapps.favoriteartistsongsandroid.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class SongResults(
    @JsonProperty("resultCount") val resultCount: Int,
    @JsonProperty("results") val results: List<Song>
): Parcelable

@Parcelize
data class Song(
    @JsonProperty("wrapperType") val wrapperType: String,
    @JsonProperty("kind") val kind: String,
    @JsonProperty("artistId") val artistId: Long,
    @JsonProperty("collectionId") val collectionId: Long,
    @JsonProperty("trackId") val trackId: Long,
    @JsonProperty("artistName") val artistName: String,
    @JsonProperty("collectionName") val collectionName: String,
    @JsonProperty("trackName") val trackName: String,
    @JsonProperty("collectionCensoredName") val collectionCensoredName: String,
    @JsonProperty("trackCensoredName") val trackCensoredName: String,
    @JsonProperty("collectionArtistId") val collectionArtistId: Long?,
    @JsonProperty("collectionArtistName") val collectionArtistName: String?,
    @JsonProperty("artistViewUrl") val artistViewUrl: String,
    @JsonProperty("collectionViewUrl") val collectionViewUrl: String,
    @JsonProperty("collectionArtistViewUrl") val collectionArtistViewUrl: String?,
    @JsonProperty("trackViewUrl") val trackViewUrl: String,
    @JsonProperty("previewUrl") val previewUrl: String?,
    @JsonProperty("artworkUrl30") val artworkUrl30: String,
    @JsonProperty("artworkUrl60") val artworkUrl60: String,
    @JsonProperty("artworkUrl100") val artworkUrl100: String,
    @JsonProperty("collectionPrice") val collectionPrice: Double,
    @JsonProperty("trackPrice") val trackPrice: Double,
    @JsonProperty("releaseDate") val releaseDate: Date?,
    @JsonProperty("collectionExplicitness") val collectionExplicitness: String,
    @JsonProperty("trackExplicitness") val trackExplicitness: String,
    @JsonProperty("discCount") val discCount: Int,
    @JsonProperty("discNumber") val discNumber: Int,
    @JsonProperty("trackCount") val trackCount: Int,
    @JsonProperty("trackNumber") val trackNumber: Int,
    @JsonProperty("trackTimeMillis") val trackTimeMillis: Long,
    @JsonProperty("country") val country: String,
    @JsonProperty("currency") val currency:  String,
    @JsonProperty("primaryGenreName") val primaryGenreName: String,
    @JsonProperty("contentAdvisoryRating") val contentAdvisoryRating: String?,
    @JsonProperty("isStreamable") val isStreamable: Boolean
): Parcelable
