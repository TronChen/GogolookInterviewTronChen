package com.example.gogolookinterviewtronchen.imageResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import app.appworks.school.stylish.network.LoadApiStatus
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchImage
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import com.example.gogolookinterviewtronchen.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ImageResultViewModel(
    private val repository: GogolookRepository,
    private val inputString : String
) : ViewModel () {

    var searchString : String? = null

    val searchItem = MutableLiveData<SearchResult>()

    var searchImage = MutableLiveData<List<SearchImage>>()

    private val sourceFactory = PagingDataSourceFactory(inputString)

    val pagingDataProducts: LiveData<PagedList<SearchImage>> = sourceFactory.toLiveData(6, null)

    // Handle load api status
    val status: LiveData<LoadApiStatus> = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.statusInitialLoad
    }

    // Handle load api error
    val error: LiveData<String> = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.errorInitialLoad
    }

    // status for the loading icon of swl
    private val _refreshStatus = MutableLiveData<Boolean>()

    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    init {
        searchString = inputString
//        getSearchResult(true,inputString)
    }

//    private fun getSearchResult(isInitial: Boolean = false , inputString: String) {
//
//        coroutineScope.launch {
//
//            if (isInitial) _status.value = LoadApiStatus.LOADING
//
//            val result = repository.getSearchResult(inputString)
//
//            searchItem.value = when (result) {
//                is AppResult.Success -> {
//                    _error.value = null
//                    if (isInitial) _status.value = LoadApiStatus.DONE
//                    result.data
//                }
//                is AppResult.Fail -> {
//                    _error.value = result.error
//                    if (isInitial) _status.value = LoadApiStatus.ERROR
//                    null
//                }
//                is AppResult.Error -> {
//                    _error.value = result.exception.toString()
//                    if (isInitial) _status.value = LoadApiStatus.ERROR
//                    null
//                }
//                else -> {
//                    _error.value = Util.getString(R.string.you_know_nothing)
//                    if (isInitial) _status.value = LoadApiStatus.ERROR
//                    null
//                }
//            }
//            _refreshStatus.value = false
//        }
//    }

//    fun refresh() {
//        if (status.value != LoadApiStatus.LOADING) {
//            getSearchResult(true,inputString)
//        }
//    }

    fun refresh() {
        if (status.value != LoadApiStatus.LOADING) {
            sourceFactory.sourceLiveData.value?.invalidate()
        }
    }

}