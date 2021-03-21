package com.example.gogolookinterviewtronchen.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchImage(
    var id : Int? = null,
    var pageURL : String? = null,
    var type : String? = null,
    var tags : String? = null,
    var previewURL : String? = null,
    var previewWidth : Int? = null,
    var previewHeight : Int? = null,
    var webformatURL : String? = null,
    var webformatWidth : Int? = null,
    var webformatHeight : Int? = null,
    var largeImageURL : String? = null,
    var fullHDURL : String? = null,
    var imageURL : String? = null,
    var imageWidth : Int? = null,
    var imageHeight : Int? = null,
    var imageSize : Int? = null,
    var views : Int? = null,
    var downloads : Int? = null,
    var favorites : Int? = null,
    var likes : Int? = null,
    var comments : Int? = null,
    var user_id : Int? = null,
    var user : String? = null,
    var userImageURL : String? = null
) : Parcelable {
}