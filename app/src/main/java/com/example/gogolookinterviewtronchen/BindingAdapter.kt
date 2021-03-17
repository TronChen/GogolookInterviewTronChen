package com.example.gogolookinterviewtronchen

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("mainImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Log.d("AnAn","imgUrl = ${it}")
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.empty_image)
                    .error(R.drawable.empty_image))
            .into(imgView)
    }
}