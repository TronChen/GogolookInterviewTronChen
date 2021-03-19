package com.example.gogolookinterviewtronchen.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    var error: String? = null,
    var total: Int? = null,
    var totalHits: Int? = null,
    var hits: List<SearchImage>? = null
    ) :Parcelable{
}
