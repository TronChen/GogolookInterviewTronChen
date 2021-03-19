package com.example.gogolookinterviewtronchen.data.source.remote

import android.util.Log
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.example.gogolookinterviewtronchen.data.source.GogolookDataSource
import com.example.gogolookinterviewtronchen.util.Util.getString
import com.example.gogolookinterviewtronchen.util.Util.isInternetConnected

object GogolookRemoteDataSource : GogolookDataSource {

    override suspend fun getSearchResult(inputString: String, page: String?): AppResult<SearchResult> {
        if (!isInternetConnected()) {
            return AppResult.Fail(getString(R.string.internet_not_connected))
        }

        return try {
            // this will run on a thread managed by Retrofit
            val listResult = GogolookApi.retrofitService.getSearchResult(inputString = inputString , page = page)

            listResult.error?.let {
                return AppResult.Fail(it)
            }
            AppResult.Success(listResult)

        } catch (e: Exception) {
            Log.w("Tron","[${this::class.simpleName}] exception=${e.message}")
            AppResult.Error(e)
        }
    }
}