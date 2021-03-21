package com.example.gogolookinterviewtronchen.imageResult

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.gogolookinterviewtronchen.data.SearchImage

class PagingDataSourceFactory(val inputString: String , val viewModel: ImageResultViewModel) : DataSource.Factory<String, SearchImage>() {

    val sourceLiveData = MutableLiveData<PagingDataSource>()

    override fun create(): DataSource<String, SearchImage> {
        val source = PagingDataSource(inputString , viewModel)
        sourceLiveData.postValue(source)
        return source
    }
}